package com.sync.applier;

import java.sql.SQLException;

import com.sync.event.DBMSEvent;

public interface Applier {

	public abstract void connect() throws Exception;

	public abstract void disconnect();

	public abstract void apply(DBMSEvent event, boolean doCommit)
			throws SQLException;

}