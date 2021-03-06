/**
 * Tungsten Scale-Out Stack
 * Copyright (C) 2009-2010 Continuent Inc.
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
 * Initial developer(s): Seppo Jaakola
 * Contributor(s): Stephane Giron
 */

package com.sync.extractor.mysql;

import com.sync.dbms.OneRowChange;
import com.sync.dbms.RowChangeData;

/**
 * @author <a href="mailto:seppo.jaakola@continuent.com">Seppo Jaakola</a>
 * @author <a href="mailto:stephane.giron@continuent.com">Stephane Giron</a>
 * @version 1.0
 */
public class DeleteRowsLogEvent extends RowsLogEvent
{

    public DeleteRowsLogEvent(byte[] buffer, int eventLength,
            FormatDescriptionLogEvent descriptionEvent,
            boolean useBytesForString) throws MySQLExtractException
    {
        super(buffer, eventLength, descriptionEvent,
                MysqlBinlog.DELETE_ROWS_EVENT, useBytesForString);
    }

    /**
     * {@inheritDoc}
     * 
     * @see com.continuent.tungsten.replicator.extractor.mysql.RowsLogEvent#processExtractedEvent(com.continuent.tungsten.replicator.dbms.RowChangeData,
     *      com.continuent.tungsten.replicator.extractor.mysql.TableMapLogEvent)
     */
    @Override
    public void processExtractedEvent(RowChangeData rowChanges,
            TableMapLogEvent map) throws ExtractorException
    {
        if (map == null)
        {
            logger.error("Delete row event for unknown table");
            throw new MySQLExtractException(
                    "Delete row event for unknown table");
        }
        OneRowChange oneRowChange = new OneRowChange();
        oneRowChange.setSchemaName(map.getDatabaseName());
        oneRowChange.setTableName(map.getTableName());
        oneRowChange.setTableId(map.getTableId());
        oneRowChange.setAction(RowChangeData.ActionType.DELETE);

        int rowIndex = 0; /* index of the row in value arrays */

        for (int i = 0; i < bufferSize;)
        {
            int length = 0;

            try
            {
                length = processExtractedEventRow(oneRowChange, rowIndex,
                        usedColumns, i, packedRowsBuffer, map, true);
            }
            catch (ExtractorException e)
            {
                logger.error(
                        "Failure while processing extracted delete row event",
                        e);
                throw (e);
            }
            rowIndex++;

            if (length == 0)
                break;
            i += length;
        }
        rowChanges.appendOneRowChange(oneRowChange);

    }

}
