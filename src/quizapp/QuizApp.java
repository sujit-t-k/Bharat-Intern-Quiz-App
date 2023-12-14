/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package quizapp;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author Sujit T K
 */
public class QuizApp extends JFrame {
    
    private final String[][] ques = new String[][]{{"Which of the following is Eastern Europian Country?","Norway","Poland","Iceland","Portugal","Poland"},
                                             {"What is the capital of Australia?","Canberra","Sydnney","Queensland","Newfoundland","Canberra"},
                                             {"By which country the gun powder was invented?","United States","China","Russia","Germany","China"},
                                             {"Which country is close to Russia geographically?","Poland","Romania","North Korea","Japan","North Korea"},
                                             {"Which country has the highest Per capita Income?","United States","Liechiensten","Luxembourg","Germany","Luxembourg"}};
    private final JButton[] btns = new JButton[4];
    private final JLabel lblQuestion = new JLabel();
    private final JPanel pnl = new JPanel();
    private final boolean[] clicked = new boolean[4];
    private byte quesNumber = -1, mark = 0;
    private boolean blnAnsweredCorrectlyInFirstAttempt = true;
    
    private void gotoNextQuestion() {     
        quesNumber++;
        if(quesNumber == 0) {
            for(int i = 0; i < ques.length; i++) {
                int randomIndex = -1;
                do {
                    randomIndex = (int) (Math.random()*ques.length);
                } while (randomIndex == i);
                String[] questionSwap = this.ques[i];
                this.ques[i] = this.ques[randomIndex];
                this.ques[randomIndex] = questionSwap;
                for(int j = 1; j < 4; j++) {
                    randomIndex = ((int) (Math.random()*4)) + 1;
                    if(j != randomIndex) {
                        String choiceSwap = questionSwap[j];
                        questionSwap[j] = questionSwap[randomIndex];
                        questionSwap[randomIndex] = choiceSwap;
                    }
                }                
            }
        }
        if(quesNumber == ques.length) {
            for(int i = 0; i < 4; i++) {                
                pnl.remove(btns[i]);
            }
            repaint();
            lblQuestion.setText("Your score : " + this.mark + " / 5");
            lblQuestion.setFont(new Font("Calibri", 36, 36));  
            lblQuestion.setBounds(110, 130, 400, 50);
            JButton btnRetry = new JButton("Retake Quiz");
            btnRetry.setForeground(Color.WHITE);
            btnRetry.setBackground(Color.BLUE);
            btnRetry.setBounds(155, 220, 170, 70);
            btnRetry.setFont(new Font("Times New Roman", 24, 24));  
            this.pnl.add(btnRetry);
            btnRetry.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseReleased(MouseEvent e) {
                    pnl.remove(btnRetry);
                    blnAnsweredCorrectlyInFirstAttempt = true;
                    lblQuestion.setBounds(30,95,400,50);  
                    lblQuestion.setFont(new Font("Calibri", 16, 16)); 
                    quesNumber = -1;
                    mark = 0;
                    gotoNextQuestion();
                    for(int i = 0; i < 4; i++) {                
                        pnl.add(btns[i]);
                    }
                    repaint();
                }
                @Override
                public void mouseEntered(MouseEvent e) {
                    btnRetry.setBackground(new Color(47, 232, 9));
                    btnRetry.setForeground(Color.BLACK);
                    btnRetry.setFont(new Font("Times New Roman", 26, 26));                    
                }
                @Override
                public void mouseExited(MouseEvent e) {
                    btnRetry.setBackground(Color.BLUE);
                    btnRetry.setForeground(Color.WHITE);
                    btnRetry.setFont(new Font("Times New Roman", 24, 24)); 
                }
            });
        } else {
            lblQuestion.setText((quesNumber+1) + ".) " + ques[quesNumber][0]);
            for(int i = 0; i < 4; i++) {
                btns[i].setText(ques[quesNumber][i+1]);                
                btns[i].setBackground(Color.ORANGE);
                clicked[i] = false;
            }
            blnAnsweredCorrectlyInFirstAttempt = true;
        }
    }
    
    private final void showGUI() {
        this.pnl.setSize(500, 400);        
        for(int i = 0; i < 4; i++) {
            final int INDEX = i;
            btns[INDEX] = new JButton();
            btns[INDEX].setBackground(Color.ORANGE);
            btns[INDEX].addMouseListener(new MouseAdapter() {
                @Override
                public void mouseReleased(MouseEvent e) {                    
                    clicked[INDEX] = true;
                    if(btns[INDEX].getText().equals(ques[quesNumber][5])) {
                        if(blnAnsweredCorrectlyInFirstAttempt) mark++;
                        gotoNextQuestion();
                    } else {
                        blnAnsweredCorrectlyInFirstAttempt = false;
                        btns[INDEX].setBackground(Color.RED);
                    }
                }
                @Override
                public void mouseEntered(MouseEvent e) {
                    btns[INDEX].setBackground(Color.YELLOW);
                }
                @Override
                public void mouseExited(MouseEvent e) {
                    btns[INDEX].setBackground((clicked[INDEX]) ? Color.RED : Color.ORANGE);
                }
            });
            pnl.add(btns[i]);
        }
        
        final int BUTTON_WIDTH = 140, BUTTON_HEIGHT = 60;
        this.btns[0].setBounds(85,160,BUTTON_WIDTH,BUTTON_HEIGHT);
        this.btns[1].setBounds(265,160,BUTTON_WIDTH,BUTTON_HEIGHT);
        this.btns[2].setBounds(85,240,BUTTON_WIDTH,BUTTON_HEIGHT);
        this.btns[3].setBounds(265,240,BUTTON_WIDTH,BUTTON_HEIGHT);
        
        this.pnl.add(this.lblQuestion);
        this.lblQuestion.setBounds(30,95,400,50);  
        this.lblQuestion.setFont(new Font("Calibri", 16, 16));        
        this.lblQuestion.setForeground(new Color(45, 223, 232));
        
        JLabel lblTitle = new JLabel("My Quiz App");
        this.pnl.add(lblTitle);
        lblTitle.setBounds(130,36,400,50);
        lblTitle.setFont(new Font("Algerian", 36, 36));
        lblTitle.setForeground(new Color(175, 45, 232));
        
        pnl.setLayout(null);
        this.add(pnl);
        this.setVisible(true);
        this.setResizable(false);
        this.setSize(500, 400);
        this.setLocationRelativeTo(null);
        this.setTitle("QUIZ APP");        
        this.pnl.setBackground(new Color(27, 18, 28));
        this.setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);
        this.gotoNextQuestion();
    }
    
    public static void main(String[] args) {
        new QuizApp().showGUI();
    }
    
}
