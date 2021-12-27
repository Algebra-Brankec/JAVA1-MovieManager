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
public class ActorTableModel extends AbstractTableModel{
    
    private static final String[] COLUMN_NAMES = {"Id", "Name", "Movie id"};
    
    private List<Actor> actors;

    public ActorTableModel(List<Actor> actors) {
        this.actors = actors;
    }
    
    public ActorTableModel() {
    }

    public void setActors(List<Actor> actors) {
        this.actors = actors;
        fireTableDataChanged();
    }

    @Override
    public int getRowCount() {
        return actors.size();
    }

    @Override
    public int getColumnCount() {
        return Actor.class.getDeclaredFields().length - 1;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch (columnIndex) {
            case 0:
                return actors.get(rowIndex).getIDActor();
            case 1:
                return actors.get(rowIndex).getName();
            case 2:
                return actors.get(rowIndex).getMovieID();
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
