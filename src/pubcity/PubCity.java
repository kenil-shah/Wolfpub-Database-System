/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pubcity;

import java.util.Scanner;
import java.sql.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author ur207
 */
public class PubCity {
    
    static final String jdbcURL = "jdbc:mariadb://classdb2.csc.ncsu.edu:3306/uvasani";
    private static Connection connection = null;
    private static Statement statement = null;
    private static ResultSet result = null;
    private static Scanner sc;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        sc = new Scanner(System.in);
        try{
            connectToDatabase();
        }
        catch(Exception e){
            e.printStackTrace();
        }
        PubCity fp = new PubCity();
        
        while(true){
            System.out.println("Categories:");
            System.out.println("1. Editing and Publishing:");
            System.out.println("2. Production of a book or issue:");
            System.out.println("3. Distribution:");
            System.out.println("4. Reports:");
            System.out.println();
            
            System.out.println("Choose category: ");

            Scanner sc = new Scanner(System.in);
            char choice = sc.next().charAt(0);
            System.out.println();
            try{
                if(choice=='1'){
                    fp.editing_publishing();
                }
                else if (choice=='2'){
                    fp.production();
                }
                else if (choice=='3'){
                    fp.distribution();
                }
                else if (choice=='4'){
                    fp.reports();
                }
                else{
                    break;
                }
            }
            catch(Exception e){
                e.printStackTrace();
            }
            
            System.out.println();
        }
    }
    
    //connects the database and instantiate the connection and statement object
    private static void connectToDatabase() throws ClassNotFoundException, SQLException {
        Class.forName("org.mariadb.jdbc.Driver");

        String user = "uvasani";
        String password = "200317621";
        try{
            connection = DriverManager.getConnection(jdbcURL, user, password);
        }catch(Exception e){
            e.printStackTrace();
        }
        statement = connection.createStatement();
    }
    
    //tested.
    public void editing_publishing(){
        System.out.println("Options:");
        System.out.println("1. Enter basic information of new publication");
        System.out.println("2. Update information of publication");
        System.out.println("3. Assign editior to publication");
        System.out.println("4. Allow editors to view their work");
        System.out.println("5. Edit table of contents of publication");
        System.out.println("------------------------------------------------");
        System.out.println("6. Assign topics to new publication");
        System.out.println("7. Assign author to the Book");
        System.out.println("8. View basic information of the Book.");
        System.out.println("9. View basic information of the Periodic publication.");
        System.out.println();
        System.out.println("choose option:");
        Scanner sc = new Scanner(System.in);
        char choice = sc.next().charAt(0);
        System.out.println();
        switch(choice){
            case '1':
                this.e1();
                break;
            case '2':
                this.e2();
                break;
            case '3':
                this.e3();
                break;
            case '4':
                this.e4();
                break;
            case '5':
                this.e5();
                break;  
            case '6':
                this.e6();
                break;
            case '7':
                this.e7();
                break;
            case '8':
                this.e8();
                break;
            case '9':
                this.e9();
                break;
            default:
                System.out.println("invalid choice");
        }
    }
    
    public void production(){
        System.out.println("Options:");
        System.out.println("1. Enter a new book edition or issue");
        System.out.println("2. Update or delete a book edition or issue");
        System.out.println("3. Enter or update article or chapter");
        System.out.println("4. Enter or update text of an article");
        System.out.println("5. Find book and article by topics");
        System.out.println("6. Find book and article by date");
        System.out.println("7. Find book and article by author name");
        System.out.println("8. Enter payment for author/editor");
        System.out.println("------------------------------------");
        System.out.println("9. Enter information about staff.");
        System.out.println();
        System.out.println("choose option:");
        Scanner sc = new Scanner(System.in);
        char choice = sc.next().charAt(0);
        System.out.println();
        switch(choice){
            case '1':
                this.p1();
                break;
            case '2':
                this.p2();
                break;
            case '3':
                this.p3();
                break;
            case '4':
                this.p4();
                break;
            case '5':
                this.p5();
                break;
            case '6':
                this.p6();
                break;
            case '7':
                this.p7();
                break;
            case '8':
                this.p8();
                break;
            case '9':
                this.p9();
                break;
            default:
                System.out.println("invalid choice");
        }
    }
    
    public void distribution(){
        System.out.println("Options:");
        System.out.println("1. Enter new distributor");
        System.out.println("2. Update distributor information");
        System.out.println("3. Delete a distributor");
        System.out.println("4. Input orders from distributor for a certain date");
        System.out.println("5. Bill distributor for an order");
        System.out.println("6. Change outstanding balance on receipt of a payment");
        System.out.println("7. Enter new Warehouse Information");
        System.out.println("8. Print all the details of particular order");
        System.out.println();
        System.out.println("choose option:");
        Scanner sc = new Scanner(System.in);
        char choice = sc.next().charAt(0);
        System.out.println();
        switch(choice){
            case '1':
                this.d1();
                break;
            case '2':
                this.d2();
                break;
            case '3':
                this.d3();
                break;
            case '4':
                this.d4();
                break;
            case '5':
                this.d5();
                break;
            case '6':
                this.d6();
                break;
            case '7':
                this.enter_warehouse();
                break;
            case '8':
                this.print_order_details();
                break;
            default:
                System.out.println("invalid choice");
        }
    }
    
    public void print_order_details()
    {
        try
        {
            System.out.println("Enter the Order ID for which you want the details");
            int n = sc.nextInt();
            sc.nextLine();
            
            result = statement.executeQuery("Select * from Orders where o_id="+n+";");
            if(!result.next())
            {
                System.out.println("Order ID doesnot exist");
                return;
            }
            result.beforeFirst();
            this.print_table(result);
            System.out.println();
            result = statement.executeQuery("Select * from Order_Contains where o_id="+n+";");
            this.print_table(result);
        }
        catch(Exception e)
        {
            System.out.println("Failure");
        }
    }
    
    public void reports(){
        System.out.println("Options:");
        System.out.println("1. Number/ price of copies of each publication bought per month");
        System.out.println("2. Total revenue of publication house");
        System.out.println("3. Total expense including shipping cost and salaries");
        System.out.println("4. Calculate current number of distributor");
        System.out.println("5. Calculate total revenue per city");
        System.out.println("6. Calculate total revenue per distributor");
        System.out.println("7. Calculate total revenue per Location");
        System.out.println("8. Calculate total payment to editor/author per work type or time period");
        System.out.println();
        System.out.println("choose option:");
        Scanner sc = new Scanner(System.in);
        char choice = sc.next().charAt(0);
        System.out.println();
        switch(choice){
            case '1':
                this.r1();
                break;
            case '2':
                this.r2();
                break;
            case '3':
                this.r3();
                break;
            case '4':
                this.r4();
                break;
            case '5':
                this.r5();
                break;
            case '6':
                this.r6();
                break;
            case '7':
                this.r7();
                break;
            case '8':
                this.r8();
                break;
            default:
                System.out.println("invalid choice");
        }
    }
    
    //enter basic information of publiation.
    //tested.
    public void e1(){
        Scanner sc = new Scanner(System.in);
        try{
            System.out.println("Type B for Book, J for journal or M for Magazine:");
            char c = sc.next().charAt(0);
            sc.nextLine();
            if(c=='B'){
                System.out.println("Enter publication ID, Title, Edition number, ISBN number, publication date(YYYY/MM/DD) and price: (comma seperated)");
                String input = sc.nextLine();
                String[] ip = input.split(",");
                System.out.println();
                
                result = statement.executeQuery("Select * from Publication "
                        + "where p_id = "+ip[0]+";");
                
                if(result.next()){
                    System.out.println("An publication with this ID is already present.");
                    return;
                }
                
                //Transaction for entering new publication
                //On successfull completion of the insert statement, transaction will commit
                //in case of failure transacation will be rolled back with appropriate message
                try{
                    connection.setAutoCommit(false);
                    statement.executeUpdate("Insert into Publication values ("+ip[0]+",'"+ip[1].toString()+"')");
                    statement.executeUpdate("Insert into Book values ("+ip[0]+")");
                    statement.executeUpdate("Insert into Orderable_Items values ("+ip[0]+","+ip[2]+","+ip[5]+",'"+ip[4].toString()+"')");
                    statement.executeUpdate("Insert into Book_Edition values ("+ip[0]+","+ip[2]+","+ip[3]+")");
                    connection.commit();                     
                    System.out.println("Publication record entered successfully.");
                }catch(Exception e){
                    System.out.println("Failure in entering publication data.");
                    connection.rollback();
                } finally{
                    connection.setAutoCommit(true);
                }
            }
            else if(c=='J' || c=='M'){
                String type;
                if(c=='J'){
                    type = "Journal";
                }else{
                    type = "Magazine";
                }
                
                System.out.println("Enter publication ID, Title, Issue number, Periodicty, publication date(YYYY/MM/DD) and price: (comma seperated)");
                String input = sc.nextLine();
                String[] ip = input.split(",");
                System.out.println();
                
                result = statement.executeQuery("Select * from Orderable_Items "
                        + "where p_id = "+ip[0]+" and edition_issue = "+ip[2]+";");
                
                if(result.next()){
                    System.out.println("An publication with this ID is already present.");
                    return;
                }
                
                //Transaction for entering new Journal or Maagazine
                //On successfull completion of the insert statement, transaction will commit
                //in case of failure transacation will be rolled back with appropriate message
                try{
                    connection.setAutoCommit(false);
                    statement.executeUpdate("Insert into Publication values ("+ip[0]+",'"+ip[1].trim()+"')");
                    statement.executeUpdate("Insert into Periodic_Publication values ("+ip[0]+",'"+ip[3].trim()+"','"+type+"')");
                    statement.executeUpdate("Insert into Orderable_Items values ("+ip[0]+","+ip[2]+","+ip[5]+",'"+ip[4].trim()+"')");
                    statement.executeUpdate("Insert into Periodic_Issue values ("+ip[0]+","+ip[2]+")");
                    connection.commit();                     
                    System.out.println("Publication record entered successfully.");
                }catch(Exception e){
                    connection.rollback();
                    System.out.println("Failure in entering publication data.");
                } finally{
                    connection.setAutoCommit(true);
                } 
            } 
            else{
                System.out.println("Invaid input");
            }
        }
        catch(Exception e){
            System.out.println("Error from the database");
        }

    }
    
    //update publication details
    //tested.
    public void e2(){
        Scanner sc = new Scanner(System.in);
        try{
            System.out.println("Type B for updating book and P for updating periodic publication.");
            char c = sc.next().charAt(0);
            sc.nextLine();
            if(c=='B'){
                System.out.println("Enter Book id and edition number: (comma seperated)");
                String e2_input = sc.nextLine();
                String[] e2_ia = e2_input.split(",");
                
                result = statement.executeQuery(" Select p_id, edition_issue, title, isbn, price, publication_date from "
                        + "Publication Natural join Book Natural join Book_Edition be Natural join Orderable_Items oi "
                        + "where oi.edition_issue=be.edition and p_id="+e2_ia[0]+" and edition = "+e2_ia[1]+";");
                
                if(!result.next()){
                    System.out.println("NO such book edition found.");
                }else{
                    result.beforeFirst();
                    System.out.println("current details of the publication");
                    this.print_table(result);

                    System.out.println("enter column name with new value that you wants to update: (comma seperated)");
                    String name_value = sc.nextLine();
                    String[] nv_array = name_value.split(",");

                    if(nv_array[0].equals("p_id")){
                        statement.executeUpdate("UPDATE Publication SET "+nv_array[0]+" = "+nv_array[1]+" WHERE p_id = "+e2_ia[0]+";");
                        System.out.println("Updated successfully");
                    }else if(nv_array[0].equals("title")){
                        statement.executeUpdate("UPDATE Publication SET "+nv_array[0]+" = '"+nv_array[1]+"' WHERE p_id = "+e2_ia[0]+";");
                        System.out.println("Updated successfully");
                    }else if(nv_array[0].equals("isbn")){
                        statement.executeUpdate("UPDATE Book_Edition SET "+nv_array[0]+" = '"+nv_array[1]+"' WHERE p_id = "+e2_ia[0]+" and edition = "+e2_ia[1]+";");
                        System.out.println("Updated successfully");
                    }else if(nv_array[0].equals("edition_issue") || nv_array[0].equals("price")){
                        statement.executeUpdate("UPDATE Orderable_Items SET "+nv_array[0]+" = "+nv_array[1]+" WHERE p_id = "+e2_ia[0]+" and edition_issue = "+e2_ia[1]+";");
                        System.out.println("Updated successfully");
                    }else if(nv_array[0].equals("publication_date")){
                        statement.executeUpdate("UPDATE Orderable_Items SET "+nv_array[0]+" = '"+nv_array[1]+"' WHERE p_id = "+e2_ia[0]+" and edition_issue = "+e2_ia[1]+";");
                        System.out.println("Updated successfully");
                    }else{
                        System.out.println("Incorrect name of column");
                    }
                }                 
            }
            else if(c=='P'){
                System.out.println("Enter Publication id and issue number: (comma seperated)");
                String e2_input = sc.nextLine();
                String[] e2_ia = e2_input.split(",");
                
                result = statement.executeQuery(" Select p_id,issue,title,period,type,price,publication_date "
                        + "from Publication Natural join Periodic_Issue pi Natural join Periodic_Publication Natural join Orderable_Items oi "
                        + "where oi.edition_issue = pi.issue and p_id="+e2_ia[0]+" and issue = "+e2_ia[1]+";");
                
                if(!result.next()){
                    System.out.println("No such issue of publication found.");
                } else{
                    result.beforeFirst();
                    System.out.println("current details of the publication");
                    this.print_table(result);

                    System.out.println("enter column name with new value that you wants to update: (comma seperated)");
                    String name_value = sc.nextLine();
                    String[] nv_array = name_value.split(",");

                    if(nv_array[0].equals("p_id")){
                        statement.executeUpdate("UPDATE Publication SET "+nv_array[0]+" = "+nv_array[1]+" WHERE p_id = "+e2_ia[0]+";");
                        System.out.println("Updated successfully");
                    }else if(nv_array[0].equals("title")){
                        statement.executeUpdate("UPDATE Publication SET "+nv_array[0]+" = '"+nv_array[1]+"' WHERE p_id = "+e2_ia[0]+";");
                        System.out.println("Updated successfully");
                    }else if(nv_array[0].equals("period") || nv_array[0].equals("type")){
                        statement.executeUpdate("UPDATE Periodic_Publication SET "+nv_array[0]+" = '"+nv_array[1]+"' WHERE p_id = "+e2_ia[0]+";");
                        System.out.println("Updated successfully");
                    }else if(nv_array[0].equals("edition_issue") || nv_array[0].equals("price")){
                        statement.executeUpdate("UPDATE Orderable_Items SET "+nv_array[0]+" = "+nv_array[1]+" WHERE p_id = "+e2_ia[0]+" and edition_issue = "+e2_ia[1]+";");
                        System.out.println("Updated successfully");
                    }else if(nv_array[0].equals("publication_date")){
                        statement.executeUpdate("UPDATE Orderable_Items SET "+nv_array[0]+" = '"+nv_array[1]+"' WHERE p_id = "+e2_ia[0]+" and edition_issue = "+e2_ia[1]+";");
                        System.out.println("Updated successfully");
                    }else{
                        System.out.println("Incorrect name of column");
                    }
                }     
            } 
            else{
                System.out.println("Invaid input");
            }
            
        }
        catch(Exception e){
            System.out.println("Error occured");
        }
    }
    
    //assign editors to the Publication
    //tested.
    //change: can check if the editor is already assigned.
    public void e3(){
        Scanner sc = new Scanner(System.in);
        try{
            System.out.println("Enter Publication id, edition or issue number, list of editor_ids for the book: (comma seperated)");
            String input = sc.nextLine();
            String[] ia = input.split(",");

            try{
                connection.setAutoCommit(false);
                for(int i=2;i<ia.length;i++){
                    statement.executeUpdate("Insert into Edits values ("+ia[i]+","+ia[0]+","+ia[1]+");");
                }
                System.out.println("Editors assigned successfully.");
                connection.commit();
            }catch(Exception e){
                System.out.println("Error occured. Rolled back the transaction.");
                connection.rollback();
            } finally{
                connection.setAutoCommit(true);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    //display information of the publication for which editor is responsible for
    //tested.
    public void e4(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter editor id for which information should be displayed:");
        int editor = sc.nextInt();
        try{
            result = statement.executeQuery("SELECT p.p_id, p.title, oi.edition_issue, oi.price, oi.publication_date\n" +
            "FROM Edits e NATURAL JOIN Orderable_Items oi NATURAL JOIN Publication p\n" +
            "WHERE e.s_id =" +editor+";");
            
            if(!result.next()){
                System.out.println("No publication found for this editor.");
            }else{
                result.beforeFirst();
                this.print_table(result);
            }
              
        } catch(Exception e){
            e.printStackTrace();
        }
    }
    
    //edit and view table of content for the Book or Periodic publication
    //tested.
    public void e5(){
        Scanner sc = new Scanner(System.in);
        try{
            System.out.println("Enter B for editing the Book content and P for editing the periodic publication content");
            char choice = sc.next().charAt(0);
            sc.nextLine();
            
            if(choice=='B' || choice=='b'){
                System.out.println("Enter Book id and edition number:(comma seperated)");
                String input = sc.nextLine();
                String[] ia = input.split(",");
                
                System.out.println("Current table of content:");
                result = statement.executeQuery("SELECT number, name as Chapter_name, text as Content\n" +
                "FROM Chapters\n" +
                "WHERE p_id = "+ia[0]+" and edition = "+ia[1]+";");
                
                this.print_table(result);
                
                System.out.println("Enter I to add new chapter, D to delete existing chapter from the book:");
                char c = sc.next().charAt(0);
                sc.nextLine();
                if(c=='I'){
                   System.out.println("Enter Chapter number, name and text (comma seperated)");
                   String chapter_details = sc.nextLine();
                   String[] cd = chapter_details.split(",");
                   
                   statement.executeUpdate("INSERT INTO Chapters VALUES ("+cd[0]+","+ia[0]+","+ia[1]+",'"+cd[2]+"','"+cd[1]+"');");
                   
                   System.out.println();
                   System.out.println("New table of content:");
                   result = statement.executeQuery("SELECT number, name as Chapter_name, text as Content\n" +
                   "FROM Chapters\n" +
                   "WHERE p_id = "+ia[0]+" and edition = "+ia[1]+";");
                
                   this.print_table(result);
                }
                else if(c=='D'){
                   System.out.println("Enter Chapter number to be deleted");
                   int chapter_number = sc.nextInt();
                   
                   statement.executeUpdate("DELETE FROM Chapters where number = "+chapter_number+" and p_id = "+ia[0]+" and edition = "+ia[1]+";");
                   
                   System.out.println();
                   System.out.println("New table of content:");
                   result = statement.executeQuery("SELECT number, name as Chapter_name, text as Content\n" +
                   "FROM Chapters\n" +
                   "WHERE p_id = "+ia[0]+" and edition = "+ia[1]+";");
                
                   this.print_table(result);
                } 
            }
            else if(choice=='P' || choice=='p'){
                System.out.println("Enter Publication id and issue number:(comma seperated)");
                String input = sc.nextLine();
                String[] ia = input.split(",");
                
                System.out.println("Current table of content:");
                result = statement.executeQuery("SELECT a.a_id, a.title, a.text, a.date\n" +
                "FROM Issue_Article ia NATURAL JOIN Article a\n" +
                "WHERE ia.p_id = "+ia[0]+" and ia.issue = "+ia[1]+";");
                
                this.print_table(result);
                
                System.out.println("Enter I to insert new article, D to delete existing article from the content:");
                char c = sc.next().charAt(0);
                sc.nextLine();
                
                if(c=='I'){
                    System.out.println("List of all articles");
                    result = statement.executeQuery("Select * from Article;");
                
                    this.print_table(result);
                    
                    System.out.println("Enter N to enter new article and A to add existing article to publication");
                    char new_c = sc.next().charAt(0);
                    sc.nextLine();
                    
                    if(new_c=='N'){
                        this.p3_1();
                        System.out.println("List of all articles");
                        result = statement.executeQuery("Select * from Article;");
                
                        this.print_table(result);
                    }
                    
                    System.out.println("Enter Artcle ID that you want to add to the publication:");
                    int article_id = sc.nextInt();
                    
                    result = statement.executeQuery("Select * from Article where a_id = "+article_id+";");
                    
                    if(!result.next()){
                        System.out.println("No article present with this id.");
                        return;
                    }
                    
                    statement.executeUpdate("INSERT INTO Issue_Article VALUES ("+ia[0]+","+ia[1]+","+article_id+");");
                    
                    System.out.println();
                    System.out.println("New table of content:");
                    result = statement.executeQuery("SELECT a.a_id, a.title, a.text, a.date\n" +
                    "FROM Issue_Article ia NATURAL JOIN Article a\n" +
                    "WHERE ia.p_id = "+ia[0]+" and ia.issue = "+ia[1]+";");
                
                    this.print_table(result);
                    
                }
                else if(c=='D'){
                   System.out.println("Enter Article id to be deleted");
                   int article = sc.nextInt();
                   
                   statement.executeUpdate("DELETE FROM Issue_Article where a_id = "+article+" and p_id = "+ia[0]+" and issue = "+ia[1]+";");
                   
                   System.out.println();
                   System.out.println("New table of content:");
                    result = statement.executeQuery("SELECT a.a_id, a.title, a.text, a.date\n" +
                    "FROM Issue_Article ia NATURAL JOIN Article a\n" +
                    "WHERE ia.p_id = "+ia[0]+" and ia.issue = "+ia[1]+";");
                   this.print_table(result);
                }
        
            }
            
        }catch(Exception e){
            System.out.println("Error occured");
        }
    }
    
    //add topics to the publication
    //tested.
    public void e6(){
        Scanner sc = new Scanner(System.in);
        try{
            System.out.println("Enter Publication id, list of topics for the publication: (comma seperated)");
            String input = sc.nextLine();
            String[] ia = input.split(",");
            
            result = statement.executeQuery("Select * from Topic;");
            ArrayList<String> topics = new ArrayList<String>();
            while(result.next()){
                topics.add(result.getString(1));
            }
            String[] topic_array = new String[topics.size()];
            topic_array = topics.toArray(topic_array);
            
            try{
                connection.setAutoCommit(false);
                for(int i=1;i<ia.length;i++){
                    if(Arrays.asList(topic_array).contains(ia[i].trim().toString())==false){
                        statement.executeUpdate("Insert into Topic values ('"+ia[i].trim()+"');");
                    }
                    statement.executeUpdate("Insert into Publication_Topics values ("+ia[0]+",'"+ia[i].trim()+"');");
                }
                
                connection.commit();
                System.out.println("topics assigned successfully.");
            }catch(Exception e){
                connection.rollback();
                System.out.println("Error occured. Transaction rolledback.");
            } finally{
                connection.setAutoCommit(true);
            }
        }catch(Exception e){
            e.printStackTrace();
        } 
    }
    
    //assign auhtor to the books
    //tested.
    public void e7(){
        Scanner sc = new Scanner(System.in);
        try{
            System.out.println("Enter Book id, list of author_ids for the book: (comma seperated)");
            String input = sc.nextLine();
            String[] ia = input.split(",");

            try{
                connection.setAutoCommit(false);
                for(int i=1;i<ia.length;i++){
                    statement.executeUpdate("Insert into Book_Authors values ("+ia[i]+","+ia[0]+");");
                }
                connection.commit();
                System.out.println("Authors assigned successfully.");
            }catch(Exception e){
                connection.rollback();
                System.out.println("error occured. Rolled back the transaction.");
            } finally{
                connection.setAutoCommit(true);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    //show information of the book
    //tested.
    public void e8(){
        try{
            System.out.println("Enter the Book id and edition number:");
            String book_info = sc.nextLine();
            String[] bi = book_info.split(",");
                    
            result = statement.executeQuery(" Select p_id, edition, title, price, publication_date, isbn "
                    + "from Publication natural join Book natural join Orderable_Items oi natural join Book_Edition be "
                    + "where oi.edition_issue=be.edition and p_id = "+bi[0]+"  and edition = "+bi[1]+";");
            
            if(!result.next()){
                System.out.println("No such book edition present.");
            }else{
                result.beforeFirst();
                System.out.println("Basic Information:");
                this.print_table(result);
                
            }
            
        }catch(Exception e){
            System.out.println("error occured.");
        }
    }
    
    //show information of the periodic issue
    //tested.
    public void e9(){
        try{
            System.out.println("Enter the Publication id and issue number:");
            String pub_info = sc.nextLine();
            String[] pi = pub_info.split(",");
                    
            result = statement.executeQuery("Select p_id, issue, title, period, type, price, publication_date "
                    + "from Publication natural join Periodic_Publication natural join Orderable_Items oi natural join Periodic_Issue pi "
                    + "where oi.edition_issue = pi.issue and p_id = "+pi[0]+" and issue = "+pi[1]+";");
            
            if(!result.next()){
                System.out.println("No such book edition present.");
            }else{
                result.beforeFirst();
                System.out.println("Basic Information:");
                this.print_table(result);
                
            }
        }catch(Exception e){
            System.out.println("error occured.");
        }
    }
    //tested.
    public void p1(){
        System.out.println("Options:\n1.Book\n2.Periodic Issue\n0.Go Back\n\nchoose option:");
        int choice = sc.nextInt();
        sc.nextLine();
        switch (choice) {
            case 1:
                p1_1();
                break;
            case 2:
                p1_2();
                break;
            default:
                break;
        }
    }
    
    //tested. 
    public void p1_1(){
        try{
            System.out.println("Enter the Book Id for which you wants to add new edition:");
            int book_id = sc.nextInt();
            sc.nextLine();
            
            result = statement.executeQuery("Select * from Book where p_id = "+book_id+";");
            
            if(!result.next()){
                System.out.println("No such book present. try again.");
            }
            else{
                System.out.println("enter Edition number, price, publication date, isbn (comma seperated)");
                String p1_1_input = sc.nextLine();
                String[] p1_1_ia = p1_1_input.split(",");
                
                try{
                    connection.setAutoCommit(false);
                    
                    statement.executeUpdate("INSERT INTO Orderable_Items VALUES (" + book_id + "," + p1_1_ia[0] + "," + p1_1_ia[1] + ", '" + p1_1_ia[2] + "');");
                    statement.executeUpdate("INSERT INTO Book_Edition VALUES (" + book_id + "," + p1_1_ia[0] + ",'" + p1_1_ia[3] + "');");
                    
                    connection.commit();
                    System.out.println("Inserted successfully.");
                }catch(Exception e){
                    System.out.println("error occured, rolled back the transaction");
                    connection.rollback();
                } finally{
                    connection.setAutoCommit(true);
                }
            }
            
        }catch(Exception e){
            System.out.println("Error occured");
        }
    }
    
    //tested
    public void p1_2(){
        try{
            System.out.println("Enter the Publication Id for which you wants to add new Issue:");
            int pub_id = sc.nextInt();
            sc.nextLine();
            
            result = statement.executeQuery("Select * from Periodic_Publication where p_id = "+pub_id+";");
            
            if(!result.next()){
                System.out.println("No such publication present. try again.");
            }
            else{
                System.out.println("enter Issue number, price, publication date (comma seperated)");
                String p1_2_input = sc.nextLine();
                String[] p1_2_ia = p1_2_input.split(",");
                
                try{
                    connection.setAutoCommit(false);
                    
                    statement.executeUpdate("INSERT INTO Orderable_Items VALUES (" + pub_id + "," + p1_2_ia[0] + "," + p1_2_ia[1] + ", '" + p1_2_ia[2] + "');");
                    statement.executeUpdate("INSERT INTO Periodic_Issue VALUES (" + pub_id + "," + p1_2_ia[0] + ");");
                    
                    connection.commit();
                    System.out.println("Inserted successfully.");
                }catch(Exception e){
                    System.out.println("error occured, rolled back the transaction");
                    connection.rollback();
                } finally{
                    connection.setAutoCommit(true);
                }
            }
            
        }catch(Exception e){
            System.out.println("Error occured");
        }
    }
    
    //tested.
    public void p2(){
        System.out.println("Options:\n1.Update Edition or Issue\n2.Delete Edition or Issue\n0.Go Back\n\nchoose option:");
        int choice = sc.nextInt();
        sc.nextLine();
        switch (choice) {
            case 1:
                this.e2();
                break;
            case 2:
                this.p2_2();
                break;
            default:
                break;
        }
    }
    
    //tested.
    public void p2_2(){
        try{
            System.out.println("Enter Publication Id and Edition/Issue number that you wants to delete: (Comma seperated)");
            String p2_2_input = sc.nextLine();
            String[] p2_2_ia = p2_2_input.split(",");
            
            result = statement.executeQuery("Select * from Orderable_Items where p_id = "+p2_2_ia[0]+" and edition_issue = "+p2_2_ia[1]+" ;");
            
            if(!result.next()){
                System.out.println("No such publication found.");
            }else{
                statement.executeUpdate("DELETE FROM Orderable_Items where p_id = "+p2_2_ia[0]+" and edition_issue = "+p2_2_ia[1]+";");
                System.out.println("Publication deleted successfully.");
            }
        }catch(Exception e){
            System.out.println("Error occured");
        }
        
    }
    
    public void p3(){
        System.out.println("Options:\n1.Add Article\n2.Update Article\n3.Add Chapter\n4.Update Chapter\n0.Go Back\n\nchoose option:");
        
        int ch = sc.nextInt();
        sc.nextLine();
        
        switch (ch) {
            case 1:
                p3_1();
                break;
            case 2:
                p3_2();
                break;
            case 3:
                p3_3();
                break;
            case 4:
                p3_4();
                break;
            default:
                break;
        }
    }
    
    //add article
    //tested
    public void p3_1(){
        
        try{
            System.out.println("Enter Article ID, text, title and Publication date: (comma seperated)");
            String article_details = sc.nextLine();
            String[] ad = article_details.split(",");
            
            result = statement.executeQuery("Select * from Article where a_id = "+ad[0]+" ;");
            
            if(result.next()){
                System.out.println("Article with this ID already present.");
            }else{
                statement.executeUpdate("Insert into Article values("+ad[0]+",'"+ad[1]+"','"+ad[2]+"','"+ad[3]+"');");
                System.out.println("Article inserted successfully.");
            }
            
        } catch(Exception e){
            System.out.println("Failure in Inserting the article");
        }
    }
    
    //update article
    //tested
    public void p3_2(){
        try{
            System.out.println("Enter the Id of the article that you want to update");
            int article_id = sc.nextInt();
            sc.nextLine();
            
            result = statement.executeQuery("Select * from Article where a_id = "+article_id+" ;");
            
            if(!result.next()){
                System.out.println("No article present with that Id. Try again");
            }
            else{
               result = statement.executeQuery("Select * from Article where a_id = "+article_id+" ;");
               System.out.println("Current details of the article:");
               this.print_table(result);
               
               System.out.println("Enter column name and new value that you want to update: (comma seperated)");
               String column_val = sc.nextLine();
               String[] cv_array = column_val.split(",");
                              
               if(cv_array[0].equals("a_id")){
                   statement.executeUpdate("Update Article set "+cv_array[0]+" = "+cv_array[1]+" where a_id = "+article_id+" ;");
               }else{
                   statement.executeUpdate("Update Article set "+cv_array[0]+" = '"+cv_array[1]+"' where a_id = "+article_id+" ;");
               }
               System.out.println("Updated successfully.");
            }
         } catch(Exception e){
            System.out.println("Error occured");
        }
    }
    
    //add chapter
    //tested
    public void p3_3(){
        try{
            System.out.println("Enter Book id and edition number to which you wants to add chapter:(comma seperated)");
            String input = sc.nextLine();
            String[] ia = input.split(",");
            
            result = statement.executeQuery("SELECT *\n" +
            "FROM Book_Edition\n" +
            "WHERE p_id = "+ia[0]+" and edition = "+ia[1]+";");
            
            if(!result.next()){
                System.out.println("No such book present. Try again!");
            }else{
                
                result = statement.executeQuery("SELECT number, name as Chapter_name, text as Content\n" +
                "FROM Chapters\n" +
                "WHERE p_id = "+ia[0]+" and edition = "+ia[1]+";"); 
                System.out.println("Current table of content:");
                this.print_table(result);

                System.out.println("Enter Chapter number, name and text (comma seperated)");
                String chapter_details = sc.nextLine();
                String[] cd = chapter_details.split(",");

                statement.executeUpdate("INSERT INTO Chapters VALUES ("+cd[0]+","+ia[0]+","+ia[1]+",'"+cd[2]+"','"+cd[1]+"');");
                System.out.println("Successfully inserted.");

                System.out.println();
                System.out.println("New table of content:");
                result = statement.executeQuery("SELECT number, name as Chapter_name, text as Content\n" +
                "FROM Chapters\n" +
                "WHERE p_id = "+ia[0]+" and edition = "+ia[1]+";");

                this.print_table(result);
            }
            
        }catch(Exception e){
            System.out.println("Error occured");
        } 
    }
    
    //update chapter
    //tested
    public void p3_4(){
        try{
            System.out.println("Enter Book id and edition number to which you wants to add chapter:(comma seperated)");
            String input = sc.nextLine();
            String[] ia = input.split(",");
            
            result = statement.executeQuery("SELECT *\n" +
            "FROM Book_Edition\n" +
            "WHERE p_id = "+ia[0]+" and edition = "+ia[1]+";");
            
            if(!result.next()){
                System.out.println("No such book present. Try again!");
            }else{
            
                System.out.println("Current table of content:");
                result = statement.executeQuery("SELECT number, name as Chapter_name, text as Content\n" +
                "FROM Chapters\n" +
                "WHERE p_id = "+ia[0]+" and edition = "+ia[1]+";");

                this.print_table(result);

                System.out.println("Enter Chapter number, column name and new value that you want to update (comma seperated)");
                String chapter_details = sc.nextLine();
                String[] cd = chapter_details.split(",");

                if(cd[1].equals("number")){
                    statement.executeUpdate("Update Chapters set "+cd[1]+" = "+cd[2]+" where number = "+cd[0]+" and p_id = "+ia[0]+" and edition = "+ia[1]+";");
                }else{
                    statement.executeUpdate("Update Chapters set "+cd[1]+" = '"+cd[2]+"' where number = "+cd[0]+" and p_id = "+ia[0]+" and edition = "+ia[1]+";");
                }

                System.out.println("Successfully Updated");

                System.out.println();
                System.out.println("New table of content:");
                result = statement.executeQuery("SELECT number, name as Chapter_name, text as Content\n" +
                "FROM Chapters\n" +
                "WHERE p_id = "+ia[0]+" and edition = "+ia[1]+";");

                this.print_table(result);
            }
        }catch(Exception e){
            System.out.println("Error occured");
        }
    }
    
    //update the text of an article
    //tested
    public void p4(){
        try{
            System.out.println("Enter the Id of the article that you want to update");
            int article_id = sc.nextInt();
            sc.nextLine();
            
            result = statement.executeQuery("Select * from Article where a_id = "+article_id+" ;");
            
            if(!result.next()){
                System.out.println("No article present with that Id. Try again");
            }
            else{
               result = statement.executeQuery("Select text from Article where a_id = "+article_id+" ;");
               System.out.println("Current text of the article:");
               this.print_table(result);
               
               System.out.println("Enter new text");
               String text_val = sc.nextLine();
                    
               statement.executeUpdate("Update Article set text = '"+text_val+"' where a_id = "+article_id+" ;");
               
               System.out.println("Updated successfully.");
            }
         } catch(Exception e){
            System.out.println("Error occured");
        }
    }
    
    //Books and article by topics
    //tested
    public void p5(){
        try{
            System.out.println("Enter the name of the topic:");
            String topic = sc.nextLine();
            
            result = statement.executeQuery("SELECT p_id as Id, title, 'book' AS Book_Article\n" +
            "FROM Publication_Topics bt NATURAL JOIN Book p\n" +
            "WHERE bt.t_name = '"+topic+"'\n" +
            "UNION\n" +
            "SELECT a_id as Id, a.title, 'article' AS Book_Article\n" +
            "FROM Article_Topics at NATURAL JOIN Article a\n" +
            "WHERE at.t_name = '"+topic+"';");
            
            if(!result.next()){
                System.out.println("No such book or article found");
            }else{
                result.beforeFirst();
                this.print_table(result);
            }
            
        }catch(Exception e){
            System.out.println("error occured");
            e.printStackTrace();
        }
    }
    
    //Books and article by publication date
    //tested
    public void p6(){
        try{
            System.out.println("Enter the publication date:");
            String pdate = sc.nextLine();
            
            result = statement.executeQuery("SELECT p.p_id as Id, p.title, 'book' AS Book_Article\n" +
            "FROM Orderable_Items oi NATURAL JOIN Publication p\n" +
            "WHERE oi.publication_date = '"+pdate+"'\n" +
            "UNION\n" +
            "SELECT a.a_id as Id, a.title, 'article' AS Book_Article\n" +
            "FROM Article a\n" +
            "WHERE a.date = '"+pdate+"';");
            
            if(!result.next()){
                System.out.println("No such book or article found");
            }else{
                result.beforeFirst();
                this.print_table(result);
            }
            
        }catch(Exception e){
            System.out.println("error occured");
        }
    }
    
    //Book and article by author's name
    //tested
    public void p7(){
        try{
            System.out.println("Enter the name of the author:");
            String author = sc.nextLine();
            
            result = statement.executeQuery("SELECT p.p_id as Id, p.title, 'book' AS Book_Article\n" +
            "FROM Book_Authors ba NATURAL JOIN Publication p NATURAL JOIN Staff s\n" +
            "WHERE s.name = '"+author+"'\n" +
            "UNION\n" +
            "SELECT a.a_id as Id, a.title, 'article' AS Book_Article\n" +
            "FROM Article a NATURAL JOIN Article_Journalist aj NATURAL JOIN Staff s\n" +
            "WHERE s.name = '"+author+"';");
            
            if(!result.next()){
                System.out.println("No such book or article found");
            }else{
                result.beforeFirst();
                this.print_table(result);
            }
            
        }catch(Exception e){
            System.out.println("error occured");
        }
    }
    
    //enter payment for author
    public void p8(){
        System.out.println("Options:\n1.Enter Payment\n2.Claim payment\n0.Go Back\n\nchoose option:");
        
        int ch = sc.nextInt();
        sc.nextLine();
        
        switch (ch) {
            case 1:
                p8_1();
                break;
            case 2:
                p8_2();
                break;
            default:
                break;
        }
    }
    
    public void p8_1(){
        try{
            System.out.println("Enter transaction id, Staff id, amount and release date for the payment.");
            String p8_1 = sc.nextLine();
            String[] ta = p8_1.split(",");
            
            result = statement.executeQuery("Select * from Transactions where t_id = "+ta[0]+";");
            
            if(result.next()){
                System.out.println("Transaction with this ID already present.");
            }else{
                try{
                    connection.setAutoCommit(false);

                    statement.executeUpdate("Insert into Transactions values ("+ta[0]+","+ta[2]+")");
                    statement.executeUpdate("Insert into Salary_Payments values ("+ta[0]+",'"+ta[3]+"',NULL,"+ta[1]+")");

                    System.out.println("Payment inserted successfully");
                    connection.commit();
                }catch(Exception e){
                    connection.rollback();
                    System.out.println("Error occured. Transaction rolled back");
                }finally{
                    connection.setAutoCommit(true);
                }
            }
           
        }catch(Exception e){
            System.out.println("error occured.");
        }
    }
    
    public void p8_2(){
        try{
            System.out.println("Enter the staff id for which you want to claim the payment (comma seperated)");
            int s_id = sc.nextInt();
            sc.nextLine();
            
            result = statement.executeQuery("select * from Transactions Natural join Salary_Payments "
                        + "where s_id = "+s_id+" and claim_date is NULL;");
                
            if(!result.next()){
                System.out.println("There is no existing payment for this staff id. try again");
            }else{
                
                result = statement.executeQuery("select * from Transactions Natural join Salary_Payments "
                        + "where s_id = "+s_id+" and claim_date is NULL;");
                
                System.out.println("All pending payments");
                this.print_table(result);
                
                System.out.println("Enter the transaction id and claim date (comma seperated)");
                String claim_data = sc.nextLine();
                String[] cd = claim_data.split(",");
                
                statement.executeUpdate("UPDATE Salary_Payments SET claim_date = '"+cd[1]+"' WHERE t_id = "+cd[0]+";");
            
            }
            
        }catch(Exception e){
            System.out.println("Error occured");
        }
    }
    

    //Enter Warehouse Details
    public void enter_warehouse()
    {
        try
        {
            System.out.println("Enter Distributor ID");
            int d_id = sc.nextInt();
            sc.nextLine();
            
            result = statement.executeQuery("Select * From Distributor where d_id="+d_id+";");
            if(!result.next())
            {
               System.out.println("Distributor with this ID doesn't exist");
               return;
            }
            
            System.out.println("Enter Warehouse ID, address and City for Warehouse");
            String input = sc.nextLine();
            String[] w = input.split(",");
            
            result = statement.executeQuery("Select * From Warehouse where w_id="+w[0]+" and "+"d_id="+d_id+";");
            
            if(result.next())
            {
                System.out.println("Warehouse with this ID is already present in the database");
                return;
            }
            
            
            statement.executeUpdate("Insert into Warehouse values ("+d_id+","+w[0]+",'"+w[1]+"','"+w[2]+"');");
            
        }
        catch(Exception e)
        {
            System.out.println("Error has occured");
        }
    }
    
    //Insert into distributor

    //enter staff information
    //tested.
    public void p9(){
        try{
            System.out.println("Enter A for auhtor, E for editor and J for journalist.");
            char c = sc.next().charAt(0);
            sc.nextLine();
            
            System.out.println("Enter ");
                
            if(c=='A'){
                System.out.println("Enter Id, name, salary(NULL if invited), age, gender(M/F/O), phone number, email and address (comma seperated)");
                String input = sc.nextLine();
                String[] ia = input.split(",");
                
                try{
                    connection.setAutoCommit(false);
                    
                    statement.executeUpdate("Insert into Staff values ("+ia[0]+",'"+ia[1]+"',"+ia[2]+","+ia[3]+",'"+ia[4]+"','"+ia[5]+"','"+ia[6]+"','"+ia[7]+"');");
                    statement.executeUpdate("Insert into Author values ("+ia[0]+");");
                    
                    System.out.println("Inserted successfully.");
                    connection.commit();
                }catch(Exception e){
                    System.out.println("Error occured, Transaction rolled back.");
                    connection.rollback();
                }finally{
                    connection.setAutoCommit(true);
                }
            }else if(c=='E'){
                System.out.println("Enter Id, name, salary(NULL if invited), age, gender(M/F/O), phone number, email and address (comma seperated)");
                String input = sc.nextLine();
                String[] ia = input.split(",");
                
                try{
                    connection.setAutoCommit(false);
                    
                    statement.executeUpdate("Insert into Staff values ("+ia[0]+",'"+ia[1]+"',"+ia[2]+","+ia[3]+",'"+ia[4]+"','"+ia[5]+"','"+ia[6]+"','"+ia[7]+"');");
                    statement.executeUpdate("Insert into Editor values ("+ia[0]+");");
                    
                    System.out.println("Inserted successfully.");
                    connection.commit();
                }catch(Exception e){
                    System.out.println("Error occured, Transaction rolled back.");
                    connection.rollback();
                }finally{
                    connection.setAutoCommit(true);
                }
            }else if(c=='J'){
                System.out.println("Enter Id, name, salary(NULL if invited), age, gender(M/F/O), phone number, email and address (comma seperated)");
                String input = sc.nextLine();
                String[] ia = input.split(",");
                
                try{
                    connection.setAutoCommit(false);
                    
                    statement.executeUpdate("Insert into Staff values ("+ia[0]+",'"+ia[1]+"',"+ia[2]+","+ia[3]+",'"+ia[4]+"','"+ia[5]+"','"+ia[6]+"','"+ia[7]+"');");
                    statement.executeUpdate("Insert into Journalist values ("+ia[0]+");");
                    
                    System.out.println("Inserted successfully.");
                    connection.commit();
                }catch(Exception e){
                    System.out.println("Error occured, Transaction rolled back.");
                    connection.rollback();
                }finally{
                    connection.setAutoCommit(true);
                }
            }else{
                System.out.println("Invalid choice");
            }
            
        }catch(Exception e){
           System.out.println("Error occured"); 
        }
    }   
    

    public void d1(){
        try
        {
            System.out.println("Enter Distributor ID, name, phone number, contact person, type, balance of Distributor.");
            String input_d = sc.nextLine();
            String[] d = input_d.split(",");
            
            result = statement.executeQuery("Select * From Distributor where d_id="+d[0]+";");
            
            if(result.next())
            {
                System.out.println("Distributor with this ID is already present in the database");
                return;
            }
            
            
            statement.executeUpdate("Insert into Distributor values ("+d[0]+",'"+d[1]+"','"+d[2]+"','"+d[3]+"','"+d[4]+"',"+d[5]+");");
            System.out.println("Do you want to enter Warehouse details for Distributor (Y/N)");
            char c = sc.next().charAt(0);
            sc.nextLine();
            if(c == 'Y'  || c =='y')
                this.enter_warehouse();            
        }
        catch(Exception e)
        {
            System.out.println("Error has occured");
        }
    }
    
    public void d2(){
        try
        {
            System.out.println("Enter the Distributor ID which you want to update");
            int d_id = sc.nextInt();
            sc.nextLine();
            System.out.print(d_id);
            result = statement.executeQuery("SELECT * from Distributor where d_id="+d_id+";");
            if(!result.next())
            {
                System.out.println("No Record Present with this Distributor ID");
                return;
            }
            result.beforeFirst();
            this.print_table(result);
            System.out.println("Enter the Column name you want to update and also the new value (Comma Seprated)");
            String in = sc.nextLine();
            String[] c = in.split(",");
            
            if(c[0].equals("d_id"))
            {
                statement.executeUpdate("Update Distributor Set "+c[0]+" = "+c[1]+" where d_id = "+d_id+";");
            }
            else
            {
                statement.executeUpdate("Update Distributor Set "+c[0]+" = '"+c[1]+"' where d_id = "+d_id+";");
            }
        }
        catch(Exception e)
        {
            System.out.println("Error has occured");
        }
        
        
        
        
    }
    
    public void d3(){
        try
        {
            System.out.println("Enter the distributor ID which you want to delete");
            int d_id = sc.nextInt();
            sc.nextLine();
            
            result = statement.executeQuery("SELECT * from Distributor where d_id="+d_id+";");
            if(!result.next())
            {
                System.out.println("No Record Present with this Distributor ID");
                return;
            }
            result.beforeFirst();
            this.print_table(result);
            statement.executeUpdate("DELETE FROM Distributor where d_id = "+d_id+";");
            System.out.println("Delete Successfull");
            
            
        }
        catch(Exception e)
        {
            System.out.println("Error has occured");
        }
        
        //statement.executeUpdate("DELETE FROM Chapters where number = "+chapter_number+" and p_id = "+ia[0]+" and edition = "+ia[1]+";");
        
    }
    
    public void d4(){
        try
        {
            System.out.println("Enter the Order ID, Shipping Cost, Order Date, Distributor ID, Warehouse ID and delivery date");
            String in = sc.nextLine();
            String[] o = in.split(",");
            result = statement.executeQuery("Select * From Orders where o_id="+o[0]+";");
            
            if(result.next())
            {
                System.out.println("Distributor with this ID is already present in the database");
                return;
            }
            statement.addBatch("Insert into Orders Values("+o[0]+","+"NULL,"+o[1]+",'"+o[2]+"',"+o[3]+","+o[4]+",'"+o[5]+"');");
            char ch = 'y';
            do
            {
                System.out.println("Enter the Publication ID, Edition Issue, quantity and Price for the Order ID");
                String o_c = sc.nextLine();
                String[] o_con = o_c.split(",");
                statement.addBatch("Insert into Order_Contains Values("+o[0]+","+o_con[0]+","+o_con[1]+","+o_con[2]+","+o_con[3]+");");
                System.out.println("Enter Y or y you want to add more publications in order");
                ch = sc.next().charAt(0);
                sc.nextLine();
                //System.out.println(ch);
            }while(ch=='y' || ch=='Y');
            
            
            try{
                    connection.setAutoCommit(false);
                    statement.executeBatch();
                    connection.commit();                     
                    //System.out.println("Publication record entered successfully.");
                }catch(Exception e){
                    System.out.println("Failure");
                    connection.rollback();
                } finally{
                    connection.setAutoCommit(true);
                }
            
            //statement.executeUpdate("Insert into Orders values ("+o[0]+",'"+o[1]+"','"+d[2]+"','"+d[3]+"','"+d[4]+"',"+d[5]+")");
        }
        catch(Exception e)
        {
            //System.out.println(e.printStackTrace());
            System.out.println("Error has occured");
        }
        
    }
    
    public void d5(){
        
        try
        {
            System.out.println("Enter the Distributor ID for which you need to generate the bill");
            int n = sc.nextInt();
            sc.nextLine();
            
            result = statement.executeQuery("SELECT d.name as Distributor, oc.o_id as Order_ID,  sum(oc.quantity*oc.price) AS bill_amount FROM Orders o NATURAL JOIN Order_Contains oc NATURAL JOIN Distributor d WHERE o.t_id IS NULL and d.d_id ="+n+"GROUP BY d.d_id, oc.o_id;");
            this.print_table(result);
        }   
        catch(Exception e)
        {
            System.out.println("Error has occured");
        }
        
    }
    
    public void d6(){
        try
        {
            System.out.println("Enter the Transaction ID, Value of the Payment, date of payment, distributor id and order ID for which the payment is made");
            String s = sc.nextLine();
            String[] in = s.split(",");
            try{
                    connection.setAutoCommit(false);
                    statement.executeUpdate("Insert into Transactions Values("+in[0]+","+in[1]+");");
                    statement.executeUpdate("Insert into Incoming_Payments Values("+in[0]+",'"+in[2]+"',"+in[3]+");");
                    statement.executeUpdate("Update Orders SET t_id="+in[0]+" where o_id="+in[4]+";");
                    statement.executeUpdate("Update Distributor SET balance=balance-"+in[1]+"where d_id="+in[3]+";");
                    connection.commit();                     
                    //System.out.println("Publication record entered successfully.");
                }catch(Exception e){
                    System.out.println("Failure");
                    connection.rollback();
                } finally{
                    connection.setAutoCommit(true);
                }
            
            
        }
        catch(Exception e)
        {
            System.out.println("Error");
        }
        
    }
    
    public void r1(){
        
        try{
            System.out.println("Enter A for report of all the months and M for for a specific month");
            char choice = sc.next().charAt(0);
            sc.nextLine();
            if(choice=='A' || choice=='a')
            {
                result = statement.executeQuery("SELECT d.name as Distributor, p.title as Publication, oc.edition_issue, Month(o.order_date) as Month, Year(o.order_date) as Year, Sum(oc.quantity) as Number, Sum(oc.quantity*oc.price) as Total_price \n" +
                "FROM Orders o NATURAL JOIN Order_Contains oc NATURAL JOIN Publication p NATURAL JOIN Distributor d \n" +
                "GROUP BY o.d_id, oc.p_id, oc.edition_issue, Month(o.order_date), Year(o.order_date);");
        
                this.print_table(result);

            }
            else if(choice=='M' || choice=='m') 
                    {
                System.out.println("Enter the month number (From 1-12) and year number (comma seperated)");
                String choice1 = sc.nextLine();
                String[] c1 = choice1.split(",");

                result = statement.executeQuery("SELECT d.name as Distributor, p.title as Publication, oc.edition_issue, Month(o.order_date) as Month, Year(o.order_date) as Year, Sum(oc.quantity) as Number, Sum(oc.quantity*oc.price) as Total_price \n" +
                "FROM Orders o NATURAL JOIN Order_Contains oc NATURAL JOIN Publication p NATURAL JOIN Distributor d \n" +
                 "WHERE Month(o.order_date)="+c1[0]+" and Year(o.order_date)= "+c1[1]+" \n"+
                "GROUP BY o.d_id, oc.p_id, oc.edition_issue, Month(o.order_date), Year(o.order_date);"); 
            
                if(!result.next())
                {
                    {
                    System.out.println("Month and Year Combination is not present in the dataset");
                    }
                }
                else{
                    result.beforeFirst();
                    this.print_table(result);
                    }
        }
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
    
    public void r2(){
        try{    
            
            result = statement.executeQuery("SELECT Month(ip.date_of_receipt) as Month, Year(ip.date_of_receipt) as Year, sum(t.amount) as Revenue\n" +
                                            "FROM Incoming_Payments ip NATURAL JOIN Transactions t\n" +
                                            "GROUP BY Month(ip.date_of_receipt), Year(ip.date_of_receipt);");
            this.print_table(result);
            
        } catch(Exception e){
            e.printStackTrace();
        }
        
    }
    
    public void r3(){
        try{    
        result = statement.executeQuery("SELECT f.Month as Month, f.Year as Year, sum(IFNULL(f.Expence,0)) as Expence FROM\n" +
                                        "((SELECT Month(s.release_date) as Month, Year(s.release_date) as Year, sum(t.amount) as Expence FROM\n" +
                                        "Transactions t NATURAL JOIN Salary_Payments s GROUP BY Month(s.release_date), Year(s.release_date))\n" +
                                        "UNION ALL\n" +
                                        "\n" +
                                        "(SELECT Month(o.order_date) as Month, Year(o.order_date) as Year, sum(o.shipping_cost) as Expence FROM\n" +
                                        "Orders o GROUP BY Month(o.order_date), Year(o.order_date))) f GROUP BY f.Month, f.Year;");
        this.print_table(result);

        } catch(Exception e){
            e.printStackTrace();
        }        
    }
    
    public void r4(){
        try{    
        result = statement.executeQuery("SELECT count(*) as Number_Of_Distributors FROM Distributor;");
        this.print_table(result);
        
        } catch(Exception e){
            e.printStackTrace();
        }
        
    }
    
    public void r5(){
                
        try{
            System.out.println("Enter A for report of all the city and C for for a specific city");
            char choice = sc.next().charAt(0);
            sc.nextLine();
            if(choice=='A' || choice=='a')
            {
                result = statement.executeQuery("SELECT w.city as City, SUM(oc.quantity*oc.price) as Revenue\n" +
                                                "FROM Warehouse w NATURAL JOIN Orders o NATURAL JOIN Order_Contains oc\n" +
                                                "GROUP BY w.city;");
        
                this.print_table(result);

            }
            else if(choice=='C' || choice=='c') 
                {
                System.out.println("Enter the name of the city");
                String choice1 = sc.nextLine();
                result = statement.executeQuery("SELECT w.city as City, SUM(oc.quantity*oc.price) as Revenue\n" +
                                                "FROM Warehouse w NATURAL JOIN Orders o NATURAL JOIN Order_Contains oc\n" +
                                                "WHERE w.city='"+choice1+"' \n"+
                                                "GROUP BY w.city;");
            
                if(!result.next())
                {
                    {
                    System.out.println("This city is not present in the dataset");
                    }
                }
                else{
                    result.beforeFirst();
                    this.print_table(result);
                    }
        }
        }
        catch(Exception e){
            e.printStackTrace();
        }

    }
    
    public void r6(){
        try{
            System.out.println("Enter A for report of all the distributors and D for for a specific distributor");
            char choice = sc.next().charAt(0);
            sc.nextLine();
            if(choice=='A' || choice=='a')
            {
                result = statement.executeQuery("SELECT o.d_id as Distributor, SUM(oc.quantity*oc.price) as Revenue\n" +
                                                "FROM Orders o NATURAL JOIN Order_Contains oc\n" +
                                                "GROUP BY o.d_id;");
        
                this.print_table(result);

            }
            else if(choice=='D' || choice=='d') 
                {
                System.out.println("Enter the name of the distributor");
                String choice1 = sc.nextLine();
                result = statement.executeQuery("SELECT o.d_id as Distributor, SUM(oc.quantity*oc.price) as Revenue\n" +
                                                "FROM Orders o NATURAL JOIN Order_Contains oc\n" +
                                                "WHERE o.d_id='"+choice1+"' \n"+
                                                "GROUP BY o.d_id;");
            
                if(!result.next())
                {
                    {
                    System.out.println("This distributor is not present in the dataset");
                    }
                }
                else{
                    result.beforeFirst();
                    this.print_table(result);
                    }
        }
        }
        catch(Exception e){
            e.printStackTrace();
        }
        }
        

    
    public void r7(){
            try{
            System.out.println("Enter A for report of all the locations and L for for a specific lcoation");
            char choice = sc.next().charAt(0);
            sc.nextLine();
            if(choice=='A' || choice=='a')
            {
                result = statement.executeQuery("SELECT w.address as Location, SUM(oc.quantity*oc.price) as Revenue\n" +
                                        "FROM Warehouse w NATURAL JOIN Orders o NATURAL JOIN Order_Contains oc\n" +
                                        "GROUP BY w.address;");
        
                this.print_table(result);

            }
            else if(choice=='L' || choice=='l') 
                {
                System.out.println("Enter the Location");
                String choice1 = sc.nextLine();
                result = statement.executeQuery("SELECT w.address as Location, SUM(oc.quantity*oc.price) as Revenue\n" +
                                        "FROM Warehouse w NATURAL JOIN Orders o NATURAL JOIN Order_Contains oc\n" +
                                        "WHERE w.address='"+choice1+"' \n"+
                                        "GROUP BY w.address;");
            
                if(!result.next())
                {
                    {
                    System.out.println("This lcoation is not present in the dataset");
                    }
                }
                else{
                    result.beforeFirst();
                    this.print_table(result);
                    }
        }
        }
        catch(Exception e){
            e.printStackTrace();
        }
        
    }
    
    public void r8(){
        try{    
        result = statement.executeQuery("(SELECT 'Editorial work', SUM(t.amount) as Payments\n" +
                                        "FROM Transactions t NATURAL JOIN Salary_Payments sp NATURAL JOIN Editor e\n" +
                                        "WHERE sp.release_date BETWEEN '2020/01/01' AND '2020/03/31')\n" +
                                        "UNION\n" +
                                        "(SELECT 'Book Authorship', SUM(t.amount) as Payments\n" +
                                        "FROM Transactions t NATURAL JOIN Salary_Payments sp NATURAL JOIN Book_Authors ba\n" +
                                        "WHERE sp.release_date BETWEEN '2020/01/01' AND '2020/03/31')\n" +
                                        "\n" +
                                        "UNION\n" +
                                        "(SELECT 'Article Authorship', SUM(t.amount) as Payments\n" +
                                        "FROM Transactions t NATURAL JOIN Salary_Payments sp NATURAL JOIN Article_Journalist aj\n" +
                                        "WHERE sp.release_date BETWEEN '2020/01/01' AND '2020/03/31');");
        this.print_table(result);

        } catch(Exception e){
            e.printStackTrace();
        }
        
    }
    
    public void print_table(ResultSet rs){
        try{
            ResultSetMetaData rsmd = result.getMetaData();
            int columnsNumber = rsmd.getColumnCount();
            CommandLineTable st = new CommandLineTable();
            st.setShowVerticalLines(true);
            String headers[] = new String[columnsNumber];
            
            for (int i = 0; i < columnsNumber; i++) {
                headers[i] = rsmd.getColumnName(i+1);   
            }
            st.setHeaders(headers);
            while(result.next()){
                String data[] = new String[columnsNumber];
                for (int i = 0; i < columnsNumber; i++) {
                    String temp = result.getString(i+1);
                    if(rs.wasNull()){
                        data[i] = "NULL";
                    }else{
                        data[i] = temp;
                    }
                    
                }
                st.addRow(data);
            }  
            st.print();
        }
        catch(Exception e){
            e.printStackTrace();
        }
        
    }
    
    public class CommandLineTable {
        private static final String HORIZONTAL_SEP = "-";
        private String verticalSep;
        private String joinSep;
        private String[] headers;
        private List<String[]> rows = new ArrayList<>();
        private boolean rightAlign;

        public CommandLineTable() {
            setShowVerticalLines(false);
        }

        public void setRightAlign(boolean rightAlign) {
            this.rightAlign = rightAlign;
        }

        public void setShowVerticalLines(boolean showVerticalLines) {
            verticalSep = showVerticalLines ? "|" : "";
            joinSep = showVerticalLines ? "+" : " ";
        }

        public void setHeaders(String... headers) {
            this.headers = headers;
        }

        public void addRow(String... cells) {
            rows.add(cells);
        }

        public void print() {
            int[] maxWidths = headers != null ?
                    Arrays.stream(headers).mapToInt(String::length).toArray() : null;

            for (String[] cells : rows) {
                if (maxWidths == null) {
                    maxWidths = new int[cells.length];
                }
                if (cells.length != maxWidths.length) {
                    throw new IllegalArgumentException("Number of row-cells and headers should be consistent");
                }
                for (int i = 0; i < cells.length; i++) {
                    maxWidths[i] = Math.max(maxWidths[i], cells[i].length());
                }
            }

            if (headers != null) {
                printLine(maxWidths);
                printRow(headers, maxWidths);
                printLine(maxWidths);
            }
            for (String[] cells : rows) {
                printRow(cells, maxWidths);
            }
            if (headers != null) {
                printLine(maxWidths);
            }
        }

        private void printLine(int[] columnWidths) {
            for (int i = 0; i < columnWidths.length; i++) {
                String line = String.join("", Collections.nCopies(columnWidths[i] +
                        verticalSep.length() + 1, HORIZONTAL_SEP));
                System.out.print(joinSep + line + (i == columnWidths.length - 1 ? joinSep : ""));
            }
            System.out.println();
        }

        private void printRow(String[] cells, int[] maxWidths) {
            for (int i = 0; i < cells.length; i++) {
                String s = cells[i];
                String verStrTemp = i == cells.length - 1 ? verticalSep : "";
                if (rightAlign) {
                    System.out.printf("%s %" + maxWidths[i] + "s %s", verticalSep, s, verStrTemp);
                } else {
                    System.out.printf("%s %-" + maxWidths[i] + "s %s", verticalSep, s, verStrTemp);
                }
            }
            System.out.println();
        }
    }
       
}
