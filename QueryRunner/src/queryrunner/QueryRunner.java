/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package queryrunner;

import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;

import java.awt.*;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * 
 * QueryRunner takes a list of Queries that are initialized in it's constructor
 * and provides functions that will call the various functions in the QueryJDBC class 
 * which will enable MYSQL queries to be executed. It also has functions to provide the
 * returned data from the Queries. Currently the eventHandlers in QueryFrame call these
 * functions in order to run the Queries.
 */
public class QueryRunner {

    
    public QueryRunner()
    {
        this.m_jdbcData = new QueryJDBC();
        m_updateAmount = 0;
        m_queryArray = new ArrayList<>();
        m_error="";
        
        // keyed our project name
        this.m_projectTeamApplication="InfoCOVID";

        // added our 10 queries in m_queryArray
        m_queryArray.add(new QueryData("SELECT PATIENT_ID, COVID_SEVERITY FROM PATIENTS WHERE COVID_SEVERITY = \"SEVERE\""));
        m_queryArray.add(new QueryData("SELECT PATIENT_ID, COVID_SEVERITY, HEALTH_ISSUES FROM PATIENTS WHERE HEALTH_ISSUES >= 2"));
        m_queryArray.add(new QueryData("SELECT STATE_NAME, DEATH_NO FROM STATES WHERE DEATH_NO > 20000"));
        m_queryArray.add(new QueryData("SELECT STATE_NAME, CASE_NO FROM STATES WHERE CASE_NO > 100000"));
        m_queryArray.add(new QueryData("SELECT STATE_NAME, DEATH_NO FROM STATES ORDER BY DEATH_NO DESC"));
        m_queryArray.add(new QueryData("SELECT COVID_SEVERITY, count(*) FROM PATIENTS WHERE COVID_SEVERITY = \"MILD\""));
        m_queryArray.add(new QueryData("SELECT STATES.STATE_NAME, CASES.CASE_NO FROM STATES, CASES WHERE STATES.CASE_NO = CASES.CASE_NO ORDER BY CASES.CASE_NO"));
        m_queryArray.add(new QueryData("SELECT PATIENT_RACE, COUNT(*) FROM PATIENTS WHERE COVID_SEVERITY = \"SEVERE\" GROUP BY PATIENT_RACE ORDER BY COUNT(*)"));
        m_queryArray.add(new QueryData("SELECT COVID_SEVERITY, DEATH_NO FROM COVID_SEVERITY ORDER BY DEATH_NO DESC"));
        m_queryArray.add(new QueryData("SELECT STATE_NAME, COUNT(*) FROM PATIENTS GROUP BY STATE_NAME ORDER BY COUNT(*) DESC"));
    }

    public int GetTotalQueries()
    {
        return m_queryArray.size();
    }
    
    public int GetParameterAmtForQuery(int queryChoice)
    {
        QueryData e=m_queryArray.get(queryChoice);
        return e.GetParmAmount();
    }
              
    public String  GetParamText(int queryChoice, int parmnum )
    {
       QueryData e=m_queryArray.get(queryChoice);        
       return e.GetParamText(parmnum); 
    }   

    public String GetQueryText(int queryChoice)
    {
        QueryData e=m_queryArray.get(queryChoice);
        return e.GetQueryString();        
    }
    
    /**
     * Function will return how many rows were updated as a result
     * of the update query
     * @return Returns how many rows were updated
     */
    
    public int GetUpdateAmount()
    {
        return m_updateAmount;
    }
    
    /**
     * Function will return ALL of the Column Headers from the query
     * @return Returns array of column headers
     */
    public String [] GetQueryHeaders()
    {
        return m_jdbcData.GetHeaders();
    }
    
    /**
     * After the query has been run, all of the data has been captured into
     * a multi-dimensional string array which contains all the row's. For each
     * row it also has all the column data. It is in string format
     * @return multi-dimensional array of String data based on the resultset 
     * from the query
     */
    public String[][] GetQueryData()
    {
        return m_jdbcData.GetData();
    }

    public String GetProjectTeamApplication()
    {
        return m_projectTeamApplication;
    }
    public boolean  isActionQuery (int queryChoice)
    {
        QueryData e=m_queryArray.get(queryChoice);
        return e.IsQueryAction();
    }
    
    public boolean isParameterQuery(int queryChoice)
    {
        QueryData e=m_queryArray.get(queryChoice);
        return e.IsQueryParm();
    }
    
     
    public boolean ExecuteQuery(int queryChoice, String [] parms)
    {
        boolean bOK = true;
        QueryData e=m_queryArray.get(queryChoice);        
        bOK = m_jdbcData.ExecuteQuery(e.GetQueryString(), parms, e.GetAllLikeParams());
        return bOK;
    }
    
     public boolean ExecuteUpdate(int queryChoice, String [] parms)
    {
        boolean bOK = true;
        QueryData e=m_queryArray.get(queryChoice);        
        bOK = m_jdbcData.ExecuteUpdate(e.GetQueryString(), parms);
        m_updateAmount = m_jdbcData.GetUpdateCount();
        return bOK;
    }   
    
      
    public boolean Connect(String szHost, String szUser, String szPass, String szDatabase)
    {

        boolean bConnect = m_jdbcData.ConnectToDatabase(szHost, szUser, szPass, szDatabase);
        if (bConnect == false)
            m_error = m_jdbcData.GetError();        
        return bConnect;
    }
    
    public boolean Disconnect()
    {
        // Disconnect the JDBCData Object
        boolean bConnect = m_jdbcData.CloseDatabase();
        if (bConnect == false)
            m_error = m_jdbcData.GetError();
        return true;
    }
    
    public String GetError()
    {
        return m_error;
    }
 
    private QueryJDBC m_jdbcData;
    private String m_error;    
    private String m_projectTeamApplication;
    private ArrayList<QueryData> m_queryArray;  
    private int m_updateAmount;
            
    /**
     * @param args the command line arguments
     */
    
    public static void main(String[] args) {
        final QueryRunner queryrunner = new QueryRunner();

        // prompt user to pick which application he wants to use
        Scanner user = new Scanner(System.in);
        System.out.println("Please choose which applications: 'c' for console or 'g' for GUI: ");
        String userInput = user.nextLine();

        if (userInput.equals("g"))
        {
            java.awt.EventQueue.invokeLater(new Runnable() {
                public void run() {
                    new QueryFrame(queryrunner).setVisible(true);
                }            
            });
        }
        else
        {
            if (userInput.equals("c"))
            {
                String [] test = {};

                // connecting the server with all keyed host, username, password, and database
                queryrunner.Connect("cs100","mm_cpsc502101team07","mm_cpsc502101team07Pass-","mm_cpsc502101team07");

                // create a scanner to prompt user
                Scanner sc = new Scanner(System.in);
                // let user to select 1 ~ 10 queries and key -1 to end this console
                System.out.println("Please enter number of queries (1 ~ 10) or -1 to exit: ");
                System.out.println("1: Patients with Severe Cases\n2: Severe Cases with Health Issues\n3: States with >20,000deaths");
                System.out.println("4: States with > 100,000 cases\n5: State with greatest deaths \n6: Severity in patients");
                System.out.println("7: State with least number of cases \n8: Patient severity based on race \n9: Severity correlating to death");
                System.out.println("10: Patients in states");
                // save user input to variable choice
                int choice = sc.nextInt();

                // choice must be in 1 to 10 range
                while (choice > queryrunner.GetTotalQueries()) {
                    System.out.println("Invalid choices, please re-enter:");
                    choice = sc.nextInt();
                }

                // -1 to exit this while looop
                while(choice != -1) {
                    // execute query depends on user input
                    queryrunner.ExecuteQuery(choice - 1,test);
                    String [] header = queryrunner.GetQueryHeaders(); // initialized string array fr query header
                    String [][] queryData = queryrunner.GetQueryData(); // initialized 2d string array for query data

                    // print out query output by using nested loop
                    for(int j = 0; j < queryData.length; j++) {
                        for(int k = 0; k < queryData[j].length; k++) {
                            if(j == 0 && k == 0 && header.length == 1) {
                                System.out.printf("%-10s \n", header[0].toString());
                            }
                            if (j == 0 && k == 0 && header.length == 2) {
                                System.out.printf("%-13s %-10s\n", header[0].toString(),header[1].toString());
                            }
                            if (j == 0 && k == 0 && header.length == 3) {
                                System.out.printf("%-10s %-10s %-10s\n", header[0].toString(),header[1].toString(),header[2].toString());
                            }
                            System.out.printf("%-15s",queryData[j][k]);
                        }
                        System.out.println();
                    }
                    System.out.println();
                    // reprint menu for user to choose which queries
                    System.out.println("Please enter number of queries (1 ~ 10) or -1 to exit: ");
                    System.out.println("1: Patients with Severe Cases\n2: Severe Cases with Health Issues\n3: States with >20,000deaths");
                    System.out.println("4: States with > 100,000 cases\n5: State with greatest deaths \n6: Severity in patients");
                    System.out.println("7: State with least number of cases \n8: Patient severity based on race \n9: Severity correlating to death");
                    System.out.println("10: Patients in states");
                    choice = sc.nextInt();

                    // to validate choice in 1 to 10 range
                    while (choice > queryrunner.GetTotalQueries()) {
                        System.out.println("Invalid choices, please re-enter:");
                        choice = sc.nextInt();
                    }
                }
                // disconnect the server
                queryrunner.Disconnect();
            }
        }
 
    }    
}
