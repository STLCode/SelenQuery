package com.softtechlabs.selenquery.plugin.SelenTable;

import com.softtechlabs.selenquery.core.SelenElement;

import java.util.List;

/**
 * Created by MANISH on 22-10-2015.
 */
public class SelenTable {

    private List<Row> rows = null;
    private List<Column> columns = null;

    private Object rowLocator=null;
    private Object colLocator=null;
    private Object cellLocator=null;



    private Object tableLocator=null;

    public SelenTable(Object tableLocator) {
        this.tableLocator = tableLocator;
    }

    public SelenTable(Object rowLocator, Object colLocator, Object cellLocator) {
        this.rowLocator = rowLocator;
        this.colLocator = colLocator;
        this.cellLocator = cellLocator;
    }

    public SelenTable(Object rowLocator, Object colLocator) {
        this.rowLocator = rowLocator;
        this.colLocator = colLocator;
    }


    public int getRowCount(){return 0;}
    public int getColCount(){return 0;}

    public String getCellValue(int rownum, int colnum){return null;}
    public String getCellValue(int rownum, String colnumName){return null;}
    public String getCellValue(int rownum, Column column){return null;}


    public Cell getCellObj(int rownum, int colnum){return null;}
    public Row getRowObj(int rownum){return null;}
    public Column getColumnObj(int colnum){return null;}

    public int getColumIndex(String columnName){return -1;}
    public int getColumIndex(Column column){return -1;}


    public List<Row> getRows(){return rows;}
    public List<Column> getColumns(){return columns;}

    public SelenElement getChildObject(Cell cell){return null;}
    public SelenElement getChildObject(int rownum, int colnum){return null;}
    public SelenElement getChildObject(Cell cell, Object childSelector){return null;}
    public SelenElement getChildObject(int rownum, int colnum, Object childSelector){return null;}

    public int findRowNum(String ColName, String value){return 0;}
    public int findRowNum(int colnum, String value){return 0;}



}
