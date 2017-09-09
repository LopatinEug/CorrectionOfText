package sample;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.ImageInput;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import javax.swing.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

import static com.sun.org.apache.xalan.internal.xsltc.compiler.util.Type.Root;


public class Main extends Application {
    static ArrayList<String> list = new ArrayList<>();
    static String sentence;
    static String[] words;
    static ArrayList<String> incorrectWords = new ArrayList<>();
    static String fileName=null;

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Исправитель текста");
        primaryStage.setHeight(250);
        primaryStage.setWidth(600);
        Image im=new Image(getClass().getResourceAsStream("abccom_logo.png"));
        primaryStage.getIcons().add(im);
        Rectangle bg=new Rectangle(600,250,Color.BLUE);
        bg.setOpacity(0.1);

        Label lbl1=new Label("Проверить введеный текст.");
        lbl1.setLayoutX(35);
        lbl1.setLayoutY(5);
        Label lbl2=new Label("Проверить текст из файла.");
        lbl2.setLayoutX(275);
        lbl2.setLayoutY(5);
        final Label lbl3=new Label();
        lbl3.setLayoutX(100);
        lbl3.setLayoutY(55);
        lbl3.setScaleX(1.25);
        lbl3.setScaleY(1.25);

        final Label lbl4=new Label();
        lbl4.setLayoutX(10);
        lbl4.setLayoutY(100);

        final RadioButton rb1=new RadioButton();
        rb1.setLayoutX(10);
        rb1.setLayoutY(5);
        final RadioButton rb2=new RadioButton();
        rb2.setLayoutX(250);
        rb2.setLayoutY(5);
        final ToggleGroup group =new ToggleGroup();
        rb1.setToggleGroup(group);
        rb2.setToggleGroup(group);
        rb1.setUserData("Введите текст для проверки");
        rb2.setUserData("Введите имя файла");


        final Button btn1=new Button("Прочитать");
        btn1.setLayoutX(275);
        btn1.setLayoutY(30);

        final Button btn2=new Button("Проверить");
        btn2.setLayoutX(25);
        btn2.setMinWidth(100);
        btn2.setLayoutY(75);
        btn2.setVisible(false);
        final Button btn6=new Button("Сохранить");
        btn6.setLayoutX(150);
        btn6.setLayoutY(75);
        btn6.setMinWidth(100);
        btn6.setVisible(false);
        final Button btn7=new Button("Сохранить как");
        btn7.setLayoutX(275);
        btn7.setLayoutY(75);
        btn7.setMinWidth(100);
        btn7.setVisible(false);

        final TextField text=new TextField();
        text.setLayoutX(10);
        text.setLayoutY(30);
        text.setMinSize(250,10);

        final ChoiceBox chb1=new ChoiceBox();
        chb1.setLayoutX(10);
        chb1.setLayoutY(120);
        chb1.setVisible(false);
        chb1.setMaxWidth(90);

        final Button btn3=new Button("Заменить");
        btn3.setLayoutX(100);
        btn3.setLayoutY(120);
        btn3.setMinWidth(100);
        btn3.setVisible(false);
        final Button btn4=new Button("Исправить");
        btn4.setLayoutX(100);
        btn4.setLayoutY(145);
        btn4.setMinWidth(100);
        btn4.setVisible(false);
        final Button btn5=new Button("Удалить");
        btn5.setLayoutX(100);
        btn5.setLayoutY(170);
        btn5.setMinWidth(100);
        btn5.setVisible(false);


        Tooltip tip=new Tooltip("Выберите слово, чтобы исправить");
        chb1.setTooltip(tip);


        group.selectedToggleProperty().addListener(new ChangeListener<Toggle>(){
            @Override
            public void changed(ObservableValue<? extends Toggle> observableValue, Toggle toggle, Toggle t1) {
                text.setPromptText((String) group.getSelectedToggle().getUserData());
                text.setText(null);
            }
        });

        final DropShadow shadow = new DropShadow();
        lbl1.addEventHandler(MouseEvent.MOUSE_CLICKED,
                new EventHandler<MouseEvent>() {
                    @Override public void handle(MouseEvent e) {
                        rb1.setSelected(true);
                    }
                });
        lbl2.addEventHandler(MouseEvent.MOUSE_CLICKED,
                new EventHandler<MouseEvent>() {
                    @Override public void handle(MouseEvent e) {
                        rb2.setSelected(true);
                    }
                });
        btn1.addEventHandler(MouseEvent.MOUSE_ENTERED,
                new EventHandler<MouseEvent>() {
                    @Override public void handle(MouseEvent e) {
                        btn1.setEffect(shadow);
                    }
                });

        btn1.addEventHandler(MouseEvent.MOUSE_EXITED,
                new EventHandler<MouseEvent>() {
                    @Override public void handle(MouseEvent e) {
                        btn1.setEffect(null);
                    }
                });
        btn2.addEventHandler(MouseEvent.MOUSE_ENTERED,
                new EventHandler<MouseEvent>() {
                    @Override public void handle(MouseEvent e) {
                        btn2.setEffect(shadow);
                    }
                });

        btn2.addEventHandler(MouseEvent.MOUSE_EXITED,
                new EventHandler<MouseEvent>() {
                    @Override public void handle(MouseEvent e) {
                        btn2.setEffect(null);
                    }
                });
        btn3.addEventHandler(MouseEvent.MOUSE_ENTERED,
                new EventHandler<MouseEvent>() {
                    @Override public void handle(MouseEvent e) {
                        btn3.setEffect(shadow);
                    }
                });

        btn3.addEventHandler(MouseEvent.MOUSE_EXITED,
                new EventHandler<MouseEvent>() {
                    @Override public void handle(MouseEvent e) {
                        btn3.setEffect(null);
                    }
                });
        btn4.addEventHandler(MouseEvent.MOUSE_ENTERED,
                new EventHandler<MouseEvent>() {
                    @Override public void handle(MouseEvent e) {
                        btn4.setEffect(shadow);
                    }
                });

        btn4.addEventHandler(MouseEvent.MOUSE_EXITED,
                new EventHandler<MouseEvent>() {
                    @Override public void handle(MouseEvent e) {
                        btn4.setEffect(null);
                    }
                });
        btn5.addEventHandler(MouseEvent.MOUSE_ENTERED,
                new EventHandler<MouseEvent>() {
                    @Override public void handle(MouseEvent e) {
                        btn5.setEffect(shadow);
                    }
                });

        btn5.addEventHandler(MouseEvent.MOUSE_EXITED,
                new EventHandler<MouseEvent>() {
                    @Override public void handle(MouseEvent e) {
                        btn5.setEffect(null);
                    }
                });
        btn6.addEventHandler(MouseEvent.MOUSE_ENTERED,
                new EventHandler<MouseEvent>() {
                    @Override public void handle(MouseEvent e) {
                        btn6.setEffect(shadow);
                    }
                });

        btn6.addEventHandler(MouseEvent.MOUSE_EXITED,
                new EventHandler<MouseEvent>() {
                    @Override public void handle(MouseEvent e) {
                        btn6.setEffect(null);
                    }
                });
        btn7.addEventHandler(MouseEvent.MOUSE_ENTERED,
                new EventHandler<MouseEvent>() {
                    @Override public void handle(MouseEvent e) {
                        btn7.setEffect(shadow);
                    }
                });

        btn7.addEventHandler(MouseEvent.MOUSE_EXITED,
                new EventHandler<MouseEvent>() {
                    @Override public void handle(MouseEvent e) {
                        btn7.setEffect(null);
                    }
                });
        btn1.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
                if (rb2.isSelected()){
                try {
                    getSentenceFromFile(text.getText());
                    fileName = text.getText();
                    lbl3.setText(sentence);
                    btn2.setVisible(true);
                    btn6.setVisible(true);
                    btn7.setVisible(true);
                } catch (IOException e1) {
                    JOptionPane.showMessageDialog(null,"Фаил не найден","Ошибка",0);
                } catch (NullPointerException e1) {
                    JOptionPane.showMessageDialog(null,"Введите имя файла","",2);
                }}
                if (rb1.isSelected()) {
                    sentence=text.getText();
                    if(sentence==null){
                        JOptionPane.showMessageDialog(null,"Введите строку","",2);
                    }
                    else {
                        lbl3.setText(sentence);
                        btn2.setVisible(true);
                        btn6.setVisible(true);
                        btn7.setVisible(true);
                    }
                }
            }
        });

        btn2.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
                divideSentence();
                findIncorrectWords();
                if(!(incorrectWords.size()==0)){
                System.out.println();
                chb1.setItems(FXCollections.observableArrayList(incorrectWords));
                chb1.setValue(incorrectWords.get(0));
                lbl4.setText("Неверные слова:");
                chb1.setVisible(true);
                btn3.setVisible(true);
                btn4.setVisible(true);
                btn5.setVisible(true);
                lbl4.setVisible(true);}else {JOptionPane.showMessageDialog(null,"Текст без ошибок","Сообщение",1);}
            }
        });
        btn6.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
                try {


                if (fileName==null){
                    fileName= (String) JOptionPane.showInputDialog(null,"Введите имя файла","Сообщение",1);
                }
                write(fileName,sentence);}
                catch (Exception e1){JOptionPane.showMessageDialog(null,"Не верный ввод имени файла","",2);}
            }
        });
        btn7.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
                try {
                fileName= (String) JOptionPane.showInputDialog(null,"Введите имя файла","Сообщение",1);
                write(fileName,sentence);}
                catch (Exception e1){JOptionPane.showMessageDialog(null,"Не верный ввод имени файла","",2);}
            }
        });

        btn3.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
                String s= (String) chb1.getValue();
                ArrayList<String> closest=maxCounts(s);
                for (int j = 0; j < closest.size()-1; j++) {
                    if(closest.get(j+1)==closest.get(j)) closest.remove(j);
                }
                String[] o=new String[closest.size()];
                for (int i = 0; i <closest.size() ; i++) {
                    o[i]=closest.get(i);
                }
                System.out.println();
                String str;
                try{
                 str= (String) JOptionPane.showInputDialog(null,"Выберите слово","Возможные слова",3,null,o,o[0]);}
                catch (Exception e1){str="";}
                sentence=sentence.replaceAll(s,str);
                lbl3.setText(sentence);
                divideSentence();
                findIncorrectWords();
                if(incorrectWords.size()==0){JOptionPane.showMessageDialog(null,"Ошибки исправлены","Сообщение",1);
                    btn3.setVisible(false);
                    btn4.setVisible(false);
                    btn5.setVisible(false);
                    chb1.setVisible(false);
                    lbl4.setVisible(false);}else {
                chb1.setItems(FXCollections.observableArrayList(incorrectWords));
                chb1.setValue(incorrectWords.get(0));}

            }
        });

        btn4.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
                String s= (String) chb1.getValue();
                System.out.println();
                String str= (String) JOptionPane.showInputDialog(null,"Введите новое слово","Исправить слово: "+s,1);
                sentence=sentence.replaceAll(s,str);
                lbl3.setText(sentence);
                divideSentence();
                findIncorrectWords();
                if(incorrectWords.size()==0){JOptionPane.showMessageDialog(null,"Ошибки исправлены","Сообщение",1);
                    btn3.setVisible(false);
                    btn4.setVisible(false);
                    btn5.setVisible(false);
                    chb1.setVisible(false);
                    lbl4.setVisible(false);}else {
                    chb1.setItems(FXCollections.observableArrayList(incorrectWords));
                    chb1.setValue(incorrectWords.get(0));}

            }
        });


        btn5.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
                String s= (String) chb1.getValue();
                System.out.println();
                sentence=sentence.replaceAll(s,"");
                lbl3.setText(sentence);
                divideSentence();
                findIncorrectWords();
                if(incorrectWords.size()==0){
                    JOptionPane.showMessageDialog(null,"Ошибки исправлены","Сообщение",1);
                    btn3.setVisible(false);
                    btn4.setVisible(false);
                    btn5.setVisible(false);
                    chb1.setVisible(false);
                    lbl4.setVisible(false);
                }else {
                    chb1.setItems(FXCollections.observableArrayList(incorrectWords));
                    chb1.setValue(incorrectWords.get(0));}

            }
        });





        rb1.setSelected(true);
        rb2.requestFocus();




        Pane root=new Pane();
        Scene scene=new Scene(root);
        root.getChildren().addAll(bg,text,rb1,rb2,lbl1,lbl2,lbl3,lbl4,btn1,btn2,btn3,btn4,btn5,btn6,btn7,chb1);

        primaryStage.setScene(scene);
        primaryStage.show();
    }


    public static void main(String[] args) throws IOException {
        readVocabulary();
        launch(args);

    }
    //Запись в файл
    public static void write(String fileName, String text) {
        File file = new File(fileName);
        try {
            if(!file.exists()){
                file.createNewFile();
            }
            PrintWriter out = new PrintWriter(file.getAbsoluteFile());
            try {

                out.print(text);
            } finally {

                out.close();
            }
        } catch(IOException e) {
            JOptionPane.showMessageDialog(null,"Имя файла не введено","",2);
        }
    }
//Подключение словаря
    public static void readVocabulary() throws IOException {
        String file = "src\\sample\\Vocabulary.txt";
        BufferedReader reader = new BufferedReader(new FileReader(file));
        while (reader.ready()) {
            String string;
            string = reader.readLine().trim();
            list.add(string);
        }
        reader.close();
        System.out.println();
    }
//Чтение с файла
    public static void getSentenceFromFile(String filename) throws IOException {
        sentence=null;
        final String ENCODING_UTF8 = "windows-1251";
        BufferedReader reader = new BufferedReader(
                new InputStreamReader(new FileInputStream(filename)
                        //,ENCODING_UTF8
                )
        );

        while (reader.ready()) {
            sentence += reader.readLine();
            sentence += " ";
        }
        sentence = sentence.substring(4);
        reader.close();
    }
//Разделить предложение на слова
    public static void divideSentence() {
        String s1 = sentence.replaceAll("[^а-я0-9А-Я -]", "");
        words = s1.split(" ");
        System.out.println();
    }
//найти неправельные слова
    public static void findIncorrectWords() {
        ArrayList<String> inc = new ArrayList<>();
        for (int i = 0; i < words.length; i++) {
            if (!words[i].equals("-")&&!words[i].equals("")) {
                boolean b = false;
                for (String s : list) {
                    if (s.equals(words[i].toLowerCase())) {
                        b = true;
                        break;
                    }
                }
                if (!b) {
                    inc.add(words[i]);
                }
            }
        }
        incorrectWords=inc;
    }
//Методы для нахождения похожих слов
    public static int[] countLetters(String s) {
        char[] alphabet = {'а', 'б', 'в', 'г', 'д', 'е', 'й', 'ж', 'з', 'и', 'й', 'к', 'л', 'м', 'н', 'о', 'п', 'р', 'с', 'т', 'у', 'ф', 'х', 'ц', 'ч', 'ш', 'щ', 'ъ', 'ы', 'ь', 'э', 'ю', 'я', '-','0','1','2','3','4','5','6','7','8','9'};
        int[] counts = new int[alphabet.length];
        Arrays.fill(counts, 0);
        char[] s1 = s.toLowerCase().toCharArray();
        for (int i = 0; i < s1.length; i++) {
            for (int j = 0; j < alphabet.length; j++) {
                if (s1[i] == alphabet[j]) {
                    counts[j]++;
                   break;
                }
            }
        }
        return counts;
    }
    public static int compareCounts(int[]a,int[]b) {
        int Con=0;
        for (int i = 0; i <a.length ; i++) {
            if((a[i]!=0)&&(b[i]!=0)) {
                Con+=Math.min(a[i],b[i]);
            }
        }
        return Con;
    }
    public static int compareIncorrect(int[]a,int[]b) {
        int Con=0;
        for (int i = 0; i <a.length ; i++) {
            if(a[i]!=b[i]) {
                Con+=Math.max(a[i],b[i])-Math.min(a[i],b[i]);
            }
        }
        return Con;
    }




    public static ArrayList<String> maxCounts(String s){
        ArrayList<String> closer=new ArrayList<>();
            int max=0;

            int[] a=countLetters(s);
            int[] longArray1=new int[list.size()];
            int[] longArray2=new int[list.size()];
        Arrays.fill(longArray1,0);
        Arrays.fill(longArray2,0);

        for (int i = 0; i <list.size() ; i++) {
            int c[]=countLetters(list.get(i));
            longArray1[i]=compareCounts(a,c);
            longArray2[i]=compareIncorrect(a,c);
            if (longArray1[i]>max)max=longArray1[i];
        }
        for (int i = 0; i <longArray1.length ; i++) {
           if(((list.get(i).length()-s.length()==1)&&(longArray1[i]-s.length()==0)&&(longArray2[i]==1))||((list.get(i).length()-s.length()==0)&&(longArray1[i]-s.length()==-1)&&(longArray2[i]==2))||((list.get(i).length()-s.length()==-1)&&(longArray1[i]-s.length()==-1)&&(longArray2[i]==1))){
               String st=list.get(i);
               closer.add(st);}
        }
        ArrayList<String> mostCloser=new ArrayList<>();
        for (int i = 0; i <closer.size() ; i++) {
            if(closer.get(i).length()<s.length()){
                for (int j = 0; j <s.length(); j++) {
                    String str;
                    if(j==0) str=s.toLowerCase().substring(j+1);
                    else if(j==s.length()-1) str=s.toLowerCase().substring(0,j);
                    else str=s.toLowerCase().substring(0,j)+s.toLowerCase().substring(j+1);
                    if(str.equals(closer.get(i)))
                        mostCloser.add(closer.get(i));
                }
            }
            if(closer.get(i).length()==s.length()){
                int n=0;
                for (int j = 0; j <s.length(); j++) {
                   if(closer.get(i).charAt(j)==s.toLowerCase().charAt(j))n++;
                }
                if (n==s.length()-1)
                    mostCloser.add(closer.get(i));
            }
            if(closer.get(i).length()>s.length()){
                for (int j = 0; j <closer.get(i).length(); j++) {
                    String str;
                    if(j==0) str=closer.get(i).substring(j+1);
                    else if(j==s.length()-1) str=closer.get(i).substring(0,j);
                    else str=closer.get(i).substring(0,j)+closer.get(i).substring(j+1);
                    if(str.equals(s.toLowerCase()))
                        mostCloser.add(closer.get(i));
                }
            }
        }
        for (int j = 0; j < mostCloser.size()-1; j++) {
            for (int i = j+1; i <mostCloser.size() ; i++) {
                if(mostCloser.get(j).equals(mostCloser.get(i)))mostCloser.remove(j);

            }
        }
        return mostCloser;
        }




}


