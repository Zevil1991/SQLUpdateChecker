import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.Scanner;
import java.util.ArrayList;

public class Test {

    public static final Set<String> keywords = new HashSet<>(Arrays.asList("DEFAULT", "CONSTRAINT", "COLLATE", "PRIMARY KEY", "UNIQUE", "NOT NULL", "CHECK", "CREATE", "TABLE", "NULL", "INT", "VARCHAR", "CHAR", "BOOLEAN", "DATE", "TIME", "DECIMAL", "FLOAT", "BLOB"));
    public static final Set<String> operators = new HashSet<>(Arrays.asList(",", "(", ")", "|", "=", ">", "<", ">=", "<=", "AND", "OR", "NOT"));
    public static final Set<String> datatypes = new HashSet<>(Arrays.asList("INT", "VARCHAR", "CHAR", "BOOLEAN", "DATE", "TIME", "DECIMAL", "FLOAT", "BLOB"));
    
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        String input = s.nextLine();
        analyzeSQLColumnDefinition(input);
        boolean z = syntacticalAnalysis(input);
        if(z = true)
        {
        SemanticAnalysis(input);
        
        }
    }

    public static void analyzeSQLColumnDefinition(String input) {
        String[] tokens = input.split("\\s+");

        for (String token : tokens) {
            if (keywords.contains(token.toUpperCase())) {
                System.out.println("Keyword: " + token);
            } else if (operators.contains(token)) {
                System.out.println("Operator: " + token);
            } else {
                Matcher identifierMatcher = Pattern.compile("^[a-zA-Z_]+[a-zA-Z0-9_]*$").matcher(token);
                if (identifierMatcher.matches()) {
                    System.out.println("Identifier: " + token);
                } else {
                    System.out.println("Unknown token: " + token);
                }
            }
        }
    }

    public static void SemanticAnalysis(String input) {
        String[] tokens = input.split("\\s+");
        String name = tokens[2];
        String[] column = new String[100];
        ArrayList<String> Column = new ArrayList<>(Arrays.asList(column));
        String[] type = new String[100];
        ArrayList<String> Type = new ArrayList<>(Arrays.asList(type));
        boolean check = false;
        String yes = tokens[0] + " " + tokens[1];
        if(yes.equals("CREATE TABLE"))
            check = true;
        if(check){
            for (int i = 3; i < tokens.length; i++) {
              if (datatypes.contains(tokens[i].toUpperCase())) {
                 int x = i - 1;
                 Column.add(tokens[x]);
                  Type.add(tokens[i]);
               }
            }
        
        System.out.print("\n\nThis statement creates a table called " + name + " and defines columns labelled");
        for (int i = 0; i < Column.size(); i++) {
            if (Column.get(i) == null)
                continue;
            System.out.print(" " + Column.get(i));
        }
        System.out.print(" with data types");
        for (int i = 0; i < Type.size(); i++) {
            if (Type.get(i) == null)
                continue;
            System.out.print(" " + Type.get(i));
        }
        System.out.println(" respectively");
    }
    }

    public static boolean syntacticalAnalysis(String input) {
        String[] tokens = input.split("\\s+");
        boolean create = false, table = false;
        String yes = tokens[0] + " " + tokens[1];
        if(yes.equals("CREATE TABLE"))
        {
            create = true;
            table = true;
        }
        if(create && table)
        {
            for(int i =0 ; i < tokens.length; i++)
            {
                if(datatypes.contains(tokens[i]))
                {
                    int x = i;
                    --x;
                    if(datatypes.contains(tokens[x]) || keywords.contains(tokens[x]) || operators.contains(tokens[x]))
                    {
                        System.out.print("\nThis statement does not follow the grammatical syntax rules of the SQL column definition statement.");
                        return false;
                    }
                }
            }
            System.out.println("\nThis statement follows the grammatical syntax rules of the SQL column definition statement");
            return true;
        }
        else
        {
            System.out.print("\nThis statement does not follow the grammatical syntax rules of the SQL column definition statement.");
            return true;
        }
    }
    public static void Parsing(String input)
    {
        
    }
}
