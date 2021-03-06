/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hr.algebra;

import hr.algebra.dal.Repository;
import hr.algebra.dal.RepositoryFactory;
import hr.algebra.model.Director;
import hr.algebra.model.DirectorTableModel;
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
public class EditDirectorsPanel extends javax.swing.JPanel {

    private List<JComboBox> validationComboBox;
    private List<JTextComponent> validationFields;
    private List<JLabel> errorLabels;
    
    private Repository repository;
    private DirectorTableModel directorsTableModel;
    
    private Director selectedDirector;
    
    /**
     * Creates new form EditDirectorsPanel
     */
    public EditDirectorsPanel() {
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
        tbDirectors = new javax.swing.JTable();
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

        tbDirectors.setModel(new javax.swing.table.DefaultTableModel(
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
        tbDirectors.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbDirectorsMouseClicked(evt);
            }
        });
        tbDirectors.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tbDirectorsKeyReleased(evt);
            }
        });
        jScrollPane1.setViewportView(tbDirectors);

        jLabel1.setText("Directors");

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
                .addGap(799, 799, 799)
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(95, 95, 95)
                .addComponent(jLabel1)
                .addGap(84, 84, 84)
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
            Director director = new Director(
                    tfName.getText().trim(),
                    ((Movie)cbMovies.getSelectedItem()).getId()
            );
            repository.createDirector(director);
            directorsTableModel.setDirectors(repository.selectDirectorsFromMovie((Movie)cbMovies.getSelectedItem()));

            clearForm();
        } catch (Exception ex) {
            Logger.getLogger(PanelMenu.class.getName()).log(Level.SEVERE, null, ex);
            MessageUtils.showErrorMessage("Error", "Unable to create article!");
        }
    }//GEN-LAST:event_btnAddActionPerformed

    private void btnUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateActionPerformed
        if (selectedDirector == null) {
            MessageUtils.showInformationMessage("Wrong operation", "Please choose director to update");
            return;
        }
        try {
            Movie selectedMovie = (Movie)cbMovies.getSelectedItem();
            selectedDirector.setName(tfName.getText().trim());
            selectedDirector.setMovieID(selectedMovie.getId());

            repository.updateDirector(selectedDirector.getIDDirector(), selectedDirector);
            directorsTableModel.setDirectors(repository.selectDirectorsFromMovie(selectedMovie));

            clearForm();
        } catch (Exception ex) {
            Logger.getLogger(EditMoviesPanel.class.getName()).log(Level.SEVERE, null, ex);
            MessageUtils.showErrorMessage("Error", "Unable to update director!");
        }
    }//GEN-LAST:event_btnUpdateActionPerformed

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
        if (selectedDirector == null) {
            MessageUtils.showInformationMessage("Wrong operation", "Please choose director to delete");
            return;
        }
        if (MessageUtils.showConfirmDialog(
                "Delete director",
                "Do you really want to delete director?") == JOptionPane.YES_OPTION) {
            try {
                repository.deleteDirector(selectedDirector.getIDDirector());
                directorsTableModel.setDirectors(repository.selectDirectorsFromMovie((Movie)cbMovies.getSelectedItem()));

                clearForm();
            } catch (Exception ex) {
                Logger.getLogger(EditMoviesPanel.class.getName()).log(Level.SEVERE, null, ex);
                MessageUtils.showErrorMessage("Error", "Unable to delete director!");
            }
        }
    }//GEN-LAST:event_btnDeleteActionPerformed

    private void cbMoviesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbMoviesActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbMoviesActionPerformed

    private void tfNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfNameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tfNameActionPerformed

    private void tbDirectorsKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbDirectorsKeyReleased
        showArticle();
    }//GEN-LAST:event_tbDirectorsKeyReleased

    private void tbDirectorsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbDirectorsMouseClicked
        showArticle();
    }//GEN-LAST:event_tbDirectorsMouseClicked

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
        int selectedRow = tbDirectors.getSelectedRow();
        int rowIndex = tbDirectors.convertRowIndexToModel(selectedRow);
        int selectedDirectorId = (int) directorsTableModel.getValueAt(rowIndex, 0);

        try {
            Optional<Director> optArticle = repository.selectDirector(selectedDirectorId);
            if (optArticle.isPresent()) {
                selectedDirector = optArticle.get();
                fillForm(selectedDirector);
            }
        } catch (Exception ex) {
            Logger.getLogger(PanelMenu.class.getName()).log(Level.SEVERE, null, ex);
            MessageUtils.showErrorMessage("Error", "Unable to show movie!");
        }
    }
    
    private void loadTable() {
        tbDirectors.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tbDirectors.setAutoCreateRowSorter(true);
        tbDirectors.setRowHeight(25);
        
        try {
            Movie selectedDirector = (Movie)cbMovies.getSelectedItem();
            directorsTableModel = new DirectorTableModel(repository.selectDirectorsFromMovie(selectedDirector));
        } catch(Exception e) {
            directorsTableModel = new DirectorTableModel(new ArrayList<>());
        }
        
        tbDirectors.setModel(directorsTableModel);
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
        selectedDirector = null;
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
    
    private void fillForm(Director director) {
        //cbMovies.set("test");
        tfName.setText(director.getName());
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
    private javax.swing.JTable tbDirectors;
    private javax.swing.JTextField tfName;
    // End of variables declaration//GEN-END:variables
}
