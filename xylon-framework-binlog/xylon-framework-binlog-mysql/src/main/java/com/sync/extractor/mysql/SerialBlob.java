package com.sync.extractor.mysql;


import java.sql.Blob;
import java.sql.SQLException;

import javax.sql.rowset.serial.SerialException;

/**
 * This class defines a SerialBlob
 * 
 * @author <a href="mailto:stephane.giron@continuent.com">Stephane Giron</a>
 * @version 1.0
 */
@SuppressWarnings("serial")
public class SerialBlob extends javax.sql.rowset.serial.SerialBlob
{

    public SerialBlob(byte[] b) throws SerialException, SQLException
    {
        super(b);
    }

    public SerialBlob(Blob blob) throws SerialException, SQLException
    {
        super(blob);
    }

    @Override
    public byte[] getBytes(long pos, int length) throws SerialException
    {
        if (length <= 0)
            return new byte[0];

        return super.getBytes(pos, length);
    }
}

