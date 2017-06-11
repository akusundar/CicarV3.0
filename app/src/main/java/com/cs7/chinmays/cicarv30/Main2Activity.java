package com.cs7.chinmays.cicarv30;

import android.app.AlertDialog;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Main2Activity extends AppCompatActivity {
    private EditText edit_name;

    private EditText edit_password;
    private EditText edit_username;
    private EditText edit_role;
    private Button but_submit,btnviewAll;
    private static final String SELECT_SQL = "SELECT * FROM Signup";
    private SQLiteDatabase db;
    DatabaseHelper mydb;
    public String value;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        // createDatabase();
        mydb = new DatabaseHelper(this);
        edit_name = (EditText) findViewById(R.id.name);
        edit_username = (EditText) findViewById(R.id.username);
        edit_password= (EditText) findViewById(R.id.password);
        edit_role = (EditText) findViewById(R.id.role);
        but_submit = (Button) findViewById(R.id.submit);
        btnviewAll = (Button)findViewById(R.id.view);

        //submit.setOnClickListener(this);
        value=edit_role.getText().toString();

        AddData();
        viewAll();


    }


    public  void AddData() {
        but_submit.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        boolean isInserted = mydb.insertData(edit_name.getText().toString(),
                                edit_username.getText().toString(),
                                edit_password.getText().toString(),edit_role.getText().toString() );
                        if(isInserted == true)
                            Toast.makeText(Main2Activity.this,"Data Inserted",Toast.LENGTH_LONG).show();
                        else
                            Toast.makeText(Main2Activity.this,"Data not Inserted",Toast.LENGTH_LONG).show();
                    }
                }
        );
    }

    public void viewAll() {
        btnviewAll.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Cursor res = mydb.getAllData();
                        if(res.getCount() == 0)
                        {
                            // show message
                            showMessage("Error","Nothing found");
                            return;
                        }

                        StringBuffer buffer = new StringBuffer();

                        while (res.moveToNext()) {
                            buffer.append("Name :"+ res.getString(0)+"\n");
                            buffer.append("Username :"+ res.getString(1)+"\n");
                            buffer.append("Password :"+ res.getString(2)+"\n");
                            buffer.append("Role :"+ res.getString(3)+"\n\n");
                        }

                        // Show all data
                        showMessage("Data",buffer.toString());
                    }
                }
        );
    }

    public void showMessage(String title,String Message){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.show();
    }




}











/*
        protected void createDatabase(){
            db=openOrCreateDatabase("Cicar", Context.MODE_PRIVATE, null);
            db.execSQL("CREATE TABLE IF NOT EXISTS Signup(name VARCHAR, username VARCHAR,password VARCHAR,role VARCHAR);");
        }

        protected void insertIntoDB(){

            String name1 =name.getText().toString().trim();
            String password1 = password.getText().toString().trim();
            String username1 = username.getText().toString().trim();
            String role1 = role.getText().toString().trim();

            if(name1.equals("") || username1.equals("") || password1.equals("") || role1.equals("")){
                Toast.makeText(getApplicationContext(),"Please fill all fields", Toast.LENGTH_LONG).show();
                return;

            }

            String query = "INSERT INTO Signup  VALUES('"+name1+"', '"+username1+"','"+password1+"', '"+role1+"');";
            db.execSQL(query);
            Toast.makeText(getApplicationContext(),"Saved Successfully", Toast.LENGTH_LONG).show();
            System.out.println();




            //String query1="SELECT * FROM Signup;";
            // db.execSQL(query1);
            //System.out.println("Inserted values are"+name1+username1+password1+role1);
            //Cursor c1=db.execSQL(query1);
            //ResultSet r1=db.execSQL(query1);
            //Statement statement = shadowOf(database).getConnection().createStatement();
            //ResultSet resultSet = statement.executeQuery("SELECT * FROM table_name");
        }




        //Cursor c=db.rawQuery(SELECT_SQL,null);//c1.moveToFirst();
        //showRecords();
        /*protected void showRecords() {
            String name2 = c.getString(0);
            String username2 = c.getString(1);
            String password2= c.getString(2);
            String role2= c.getString(3);
            //editTextId.setText(name2);                make a new activity
            //editTextName.setText(username2);          with 4 blank fields
            //editTextAdd.setText(password2);           display these values there
            //editTextAdd.setText(role2);               https://www.simplifiedcoding.net/how-to-fetch-data-from-sqlite-database-in-android/
        }




        @Override
        public void onClick(View view)
        {
            if(view==submit)
            {
                insertIntoDB();
            }
        }

*/


