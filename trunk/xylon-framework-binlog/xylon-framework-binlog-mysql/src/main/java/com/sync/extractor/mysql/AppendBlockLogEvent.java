/**
 * Tungsten Scale-Out Stack
 * Copyright (C) 2010 Continuent Inc.
 * Contact: tungsten@continuent.org
 *
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of version 2 of the GNU General Public License as
 * published by the Free Software Foundation.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA 02111-1307, USA
 *
 * Initial developer(s): Stephane Giron
 * Contributor(s):
 */

package com.sync.extractor.mysql;

import java.io.IOException;

import com.sync.extractor.mysql.conversion.LittleEndianConversion;


/**
 * 
 * @author <a href="mailto:stephane.giron@continuent.com">Stephane Giron</a>
 * @version 1.0
 */
public class AppendBlockLogEvent extends LogEvent
{
    int    fileID;
    byte[] fileData;

    public AppendBlockLogEvent(byte[] buffer, int eventLength,
            FormatDescriptionLogEvent descriptionEvent)
            throws MySQLExtractException
    {
        super(buffer, descriptionEvent, MysqlBinlog.APPEND_BLOCK_EVENT);

        int commonHeaderLength, postHeaderLength;

        int fixedPartIndex;

        commonHeaderLength = descriptionEvent.commonHeaderLength;
        postHeaderLength = descriptionEvent.postHeaderLength[type - 1];

        if (logger.isDebugEnabled())
            logger.debug("event length: " + eventLength
                    + " common header length: " + commonHeaderLength
                    + " post header length: " + postHeaderLength);

        /* Read the fixed data part */
        fixedPartIndex = commonHeaderLength;

        try
        {
            /* 4 Bytes for file ID */
            fileID = LittleEndianConversion.convert4BytesToInt(buffer,
                    fixedPartIndex);
            fixedPartIndex += 4;

            /*
             * the remaining bytes represent the first bytes of the files to be
             * loaded
             */
            int dataLength = buffer.length - fixedPartIndex;
            fileData = new byte[dataLength];
            System.arraycopy(buffer, fixedPartIndex, fileData, 0, dataLength);
        }
        catch (IOException e)
        {
            logger.error("AppendBlockLogEvent parsing failed : ", e);
        }
    }

    public int getFileID()
    {
        return fileID;
    }

    public byte[] getData()
    {
        return fileData;
    }
}
