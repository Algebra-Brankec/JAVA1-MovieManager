/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hr.algebra.model;

import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author dnlbe
 */
public class MovieTableModel extends AbstractTableModel{
    
    private static final String[] COLUMN_NAMES = {"Id", "Title", "Published date", "Description", "Original name", "Time", "Picture path", "Link", "GUID", "Start date" };
    
    private List<Movie> articles;

    public MovieTableModel(List<Movie> movies) {
        this.articles = movies;
    }

    public void setMovies(List<Movie> movies) {
        this.articles = movies;
        fireTableDataChanged();
    }

    @Override
    public int getRowCount() {
        return articles.size();
    }

    @Override
    public int getColumnCount() {
        return Movie.class.getDeclaredFields().length - 1 - 3;//last number is to ignore extra fields not part of the table
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch (columnIndex) {
            case 0:
                return articles.get(rowIndex).getId();
            case 1:
                return articles.get(rowIndex).getTitle();
            case 2:
                return articles.get(rowIndex).getPublishedDate().format(Movie.DATE_FORMATTER);
            case 3:
                return articles.get(rowIndex).getDescription();
            case 4:
                return articles.get(rowIndex).getOrigName();
            case 5:
                return articles.get(rowIndex).getTime();
            case 6:
                return articles.get(rowIndex).getPicturePath();
            case 7:
                return articles.get(rowIndex).getLink();
            case 8:
                return articles.get(rowIndex).getGuid();
            case 9:
                return articles.get(rowIndex).getStartDate();
            default:
                throw new RuntimeException("No such column");
        }
    }

    @Override
    public String getColumnName(int column) {
        return COLUMN_NAMES[column];
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        switch(columnIndex) {
            case 0:
                return Integer.class;
        }
        return super.getColumnClass(columnIndex); 
    }
    
    
    
}
