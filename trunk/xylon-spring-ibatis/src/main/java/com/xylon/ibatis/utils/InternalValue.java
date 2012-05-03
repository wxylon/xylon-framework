package com.xylon.ibatis.utils;

import java.math.BigDecimal;
import java.nio.ByteBuffer;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

class InternalValue {

    private final Map<Integer, OrganizedCost> costs;
    private final static String SEP = "/";
    private final static Pattern SPLITTER = Pattern.compile(SEP);

    InternalValue(byte[] bytes) {
        if (bytes.length < 16 || bytes.length % 16 != 0) {
            throw new IllegalArgumentException("Length should be 16 times.");
        }
        ByteBuffer buffer = ByteBuffer.allocate(bytes.length).put(bytes);
        buffer.position(0);
        int times = bytes.length / 16;
        costs = new HashMap<Integer, OrganizedCost>(times);
        for (int i = 0; i < times; i++) {
            byte[] chunk = new byte[16];
            buffer.get(chunk, 0, 16);
            OrganizedCost cost = new OrganizedCost(chunk);
            costs.put(cost.getOrgId(), cost);
        }
    }

    InternalValue(String literal) {
        String[] parts = (literal == null) ? new String[0] : SPLITTER.split(literal, 0);
        costs = new HashMap<Integer, OrganizedCost>(parts.length);
        for (int i = 0; i < parts.length; i++) {
            OrganizedCost cost = new OrganizedCost(parts[i]);
            costs.put(cost.getOrgId(), cost);
        }
    }

    InternalValue() {
        this.costs = new HashMap<Integer, OrganizedCost>(10);
    }

    void putOrganizedCost(OrganizedCost cost) {
        this.costs.put(cost.getOrgId(), cost);
    }

    Map<Integer, OrganizedCost> getCosts() {
        return costs;
    }

    byte[] toBytes() {
        ByteBuffer buffer = ByteBuffer.allocate(16 * costs.size());
        for (OrganizedCost cost : costs.values()) {
            buffer.put(cost.toBytes());
        }
        return buffer.array();
    }

    public String toString() {
        StringBuilder literal = new StringBuilder(22 * costs.size());
        for (OrganizedCost part : costs.values()) {
            literal.append(part).append(SEP);
        }
        if (literal.length() > 0) {
            literal.deleteCharAt(literal.length() - SEP.length());
        }
        return literal.toString();
    }

    static class OrganizedCost {

        private final int orgId;

        private final double price;

        private final int qty;
        private final static String SEP = ",";
        private final static Pattern SPLITTER = Pattern.compile(SEP);

        OrganizedCost(byte[] bytes) {
            if (bytes.length != 16) {
                throw new IllegalArgumentException("Length should be 16.");
            }
            ByteBuffer buffer = ByteBuffer.allocate(16).put(bytes);
            this.orgId = buffer.getInt(0);
            this.price = buffer.getDouble(4);
            this.qty = buffer.getInt(12);
        }

        OrganizedCost(String literal) {
            String[] parts = (literal == null) ? new String[0] : SPLITTER.split(literal, 0);
            if (parts.length != 3) {
                throw new IllegalArgumentException("Should be splitted by comma, with 3 parts.");
            }

            this.orgId = Integer.parseInt(parts[0]);
            this.price = Double.parseDouble(parts[1]);
            this.qty = Integer.parseInt(parts[2]);
        }

        OrganizedCost(int orgId, BigDecimal price, int qty) {
            this.orgId = orgId;
            this.price = price.doubleValue();
            this.qty = qty;
        }

        BigDecimal getPrice() {
            return new BigDecimal(String.valueOf(price));
        }

        int getQty() {
            return qty;
        }

        int getOrgId() {
            return orgId;
        }

        byte[] toBytes() {
            return ByteBuffer.allocate(16).putInt(orgId).putDouble(price).putInt(qty).array();
        }

        public String toString() {
            return new StringBuilder(22).append(orgId).append(SEP).append(price).append(SEP).append(qty).toString();
        }

    }

}
