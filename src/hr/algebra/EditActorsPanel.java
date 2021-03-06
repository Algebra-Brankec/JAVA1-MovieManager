/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hr.algebra;

import hr.algebra.dal.Repository;
import hr.algebra.dal.RepositoryFactory;
import hr.algebra.model.Actor;
import hr.algebra.model.ActorTableModel;
import hr.algebra.model.Director;
import hr.algebra.model.Genre;
import hr.algebra.model.Movie;
import hr.algebra.model.MovieTableModel;
import hr.algebra.utils.FileUtils;
import hr.algebra.utils.IconUtils;
import hr.algebra.utils.MessageUtils;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import javax.swing.MutableComboBoxModel;
import javax.swing.text.JTextComponent;

/**
 *
 * @author brand
 */
public class EditActorsPanel extends javax.swing.JPanel {

    private List<JComboBox> validationComboBox;
    private List<JTextComponent> validationFields;
    private List<JLabel> errorLabels;
    
    private Repository repository;
    private ActorTableModel actorsTableModel;
    
    private Actor selectedActor;
    
    /**
     * Creates new form EditActorsPanel
     */
    public EditActorsPanel() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        btnAdd = new javax.swing.JButton();
        btnUpdate = new javax.swing.JButton();
        btnDelete = new javax.swing.JButton();
        lbIconError = new javax.swing.JLabel();
        cbMovies = new javax.swing.JComboBox<>();
        tfName = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbActors = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();

        setPreferredSize(new java.awt.Dimension(1675, 785));
        setRequestFocusEnabled(false);
        addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent evt) {
                formComponentShown(evt);
            }
        });

        jLabel2.setText("Movie");

        jLabel3.setText("Name");

        btnAdd.setText("Add");
        btnAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddActionPerformed(evt);
            }
        });

        btnUpdate.setText("Update");
        btnUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateActionPerformed(evt);
            }
        });

        btnDelete.setBackground(java.awt.Color.red);
        btnDelete.setForeground(new java.awt.Color(255, 255, 255));
        btnDelete.setText("Delete");
        btnDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteActionPerformed(evt);
            }
        });

        lbIconError.setForeground(java.awt.Color.red);

        cbMovies.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                cbMoviesFocusLost(evt);
            }
        });
        cbMovies.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbMoviesActionPerformed(evt);
            }
        });
        cbMovies.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                cbMoviesPropertyChange(evt);
            }
        });

        tfName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfNameActionPerformed(evt);
            }
        });

        tbActors.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3"
            }
        ));
        tbActors.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbActorsMouseClicked(evt);
            }
        });
        tbActors.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tbActorsKeyReleased(evt);
            }
        });
        jScrollPane1.setViewportView(tbActors);

        jLabel1.setText("Actors");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(368, 368, 368)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnDelete, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(tfName)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 353, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 204, Short.MAX_VALUE)
                        .addComponent(btnUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, 339, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(cbMovies, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(36, 36, 36)
                .addComponent(lbIconError, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(363, 363, 363))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1)
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(796, 796, 796)
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(99, 99, 99)
                .addComponent(jLabel1)
                .addGap(80, 80, 80)
                .addComponent(jLabel2)
                .addGap(9, 9, 9)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(cbMovies, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbIconError, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(3, 3, 3)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(tfName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(149, 149, 149)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(btnDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 39, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(24, 24, 24))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed
        try {
            Actor actor = new Actor(
                    tfName.getText().trim(),
                    ((Movie)cbMovies.getSelectedItem()).getId()
            );
            repository.createActor(actor);
            actorsTableModel.setActors(repository.selectActorsFromMovie((Movie)cbMovies.getSelectedItem()));

            clearForm();
        } catch (Exception ex) {
            Logger.getLogger(PanelMenu.class.getName()).log(Level.SEVERE, null, ex);
            MessageUtils.showErrorMessage("Error", "Unable to create article!");
        }
    }//GEN-LAST:event_btnAddActionPerformed

    private void btnUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateActionPerformed
        if (selectedActor == null) {
            MessageUtils.showInformationMessage("Wrong operation", "Please choose actor to update");
            return;
        }
        try {
            Movie selectedMovie = (Movie)cbMovies.getSelectedItem();
            selectedActor.setName(tfName.getText().trim());
            selectedActor.setMovieID(selectedMovie.getId());

            repository.updateActor(selectedActor.getIDActor(), selectedActor);
            actorsTableModel.setActors(repository.selectActorsFromMovie(selectedMovie));

            clearForm();
        } catch (Exception ex) {
            Logger.getLogger(EditMoviesPanel.class.getName()).log(Level.SEVERE, null, ex);
            MessageUtils.showErrorMessage("Error", "Unable to update actor!");
        }
    }//GEN-LAST:event_btnUpdateActionPerformed

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
        if (selectedActor == null) {
            MessageUtils.showInformationMessage("Wrong operation", "Please choose actor to delete");
            return;
        }
        if (MessageUtils.showConfirmDialog(
                "Delete actor",
                "Do you really want to delete actor?") == JOptionPane.YES_OPTION) {
            try {
                repository.deleteActor(selectedActor.getIDActor());
                actorsTableModel.setActors(repository.selectActorsFromMovie((Movie)cbMovies.getSelectedItem()));

                clearForm();
            } catch (Exception ex) {
                Logger.getLogger(EditMoviesPanel.class.getName()).log(Level.SEVERE, null, ex);
                MessageUtils.showErrorMessage("Error", "Unable to delete actor!");
            }
        }
    }//GEN-LAST:event_btnDeleteActionPerformed

    private void cbMoviesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbMoviesActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbMoviesActionPerformed

    private void tfNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfNameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tfNameActionPerformed

    private void tbActorsKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbActorsKeyReleased
        showArticle();
    }//GEN-LAST:event_tbActorsKeyReleased

    private void tbActorsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbActorsMouseClicked
        showArticle();
    }//GEN-LAST:event_tbActorsMouseClicked

    private void cbMoviesFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_cbMoviesFocusLost
        //loadTable();
    }//GEN-LAST:event_cbMoviesFocusLost

    private void cbMoviesPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_cbMoviesPropertyChange
        
    }//GEN-LAST:event_cbMoviesPropertyChange

    private void formComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_formComponentShown
        init();
    }//GEN-LAST:event_formComponentShown

    public void showArticle() {
        clearForm();
        int selectedRow = tbActors.getSelectedRow();
        int rowIndex = tbActors.convertRowIndexToModel(selectedRow);
        int selectedActorId = (int) actorsTableModel.getValueAt(rowIndex, 0);

        try {
            Optional<Actor> optArticle = repository.selectActor(selectedActorId);
            if (optArticle.isPresent()) {
                selectedActor = optArticle.get();
                fillForm(selectedActor);
            }
        } catch (Exception ex) {
            Logger.getLogger(PanelMenu.class.getName()).log(Level.SEVERE, null, ex);
            MessageUtils.showErrorMessage("Error", "Unable to show movie!");
        }
    }
    
    private void loadTable() {
        tbActors.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tbActors.setAutoCreateRowSorter(true);
        tbActors.setRowHeight(25);
        
        try {
            Movie selectedActor = (Movie)cbMovies.getSelectedItem();
            actorsTableModel = new ActorTableModel(repository.selectActorsFromMovie(selectedActor));
        } catch(Exception e) {
            actorsTableModel = new ActorTableModel(new ArrayList<>());
        }
        
        tbActors.setModel(actorsTableModel);
    }
    
    private void init() {
        try {
            initValidation();
            initRepository();
            initComboBox();
        } catch (Exception ex) {
            Logger.getLogger(EditMoviesPanel.class.getName()).log(Level.SEVERE, null, ex);
            MessageUtils.showErrorMessage("Unrecoverable error", "Cannot initiate the form");
            System.exit(1);
        }
    }
    
    private void initValidation() {
        validationComboBox = Arrays.asList(cbMovies);
        validationFields = Arrays.asList(tfName);
    }
    
    private void clearForm() {
        validationFields.forEach(e -> e.setText(""));
        selectedActor = null;
    }
    
    private void initRepository() throws Exception {
        repository = RepositoryFactory.getRepository();
    }
    
    private void initComboBox() throws Exception {
        List<Movie> movies = repository.selectMovies();
        
        MutableComboBoxModel<Movie> model = new DefaultComboBoxModel<>();
        for(Movie movie : movies)
        {
            model.addElement(movie); // ERROR
        }
        
        cbMovies.setModel(model);
        
        cbMovies.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent ie) {
                loadTable();
            }
        });
    }
    
    private void fillForm(Actor actor) {
        //cbMovies.set("test");
        tfName.setText(actor.getName());
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdd;
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnUpdate;
    private javax.swing.JComboBox<Movie> cbMovies;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lbIconError;
    private javax.swing.JTable tbActors;
    private javax.swing.JTextField tfName;
    // End of variables declaration//GEN-END:variables
}
