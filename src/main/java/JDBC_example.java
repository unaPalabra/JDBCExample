import java.sql.*;

public class JDBC_example {
    //Указываем драйвер базы данных
    private static final String JDBC_DRIVER = "org.postgresql.Driver";
    //Указываем URL для подключения к базе данных
    private static final String DB_URL = "jdbc:postgresql://localhost:5432/tgbot";

    // Указываем логин и пароль для подключения к базе данных
    static final String USER = "postgres";
    static final String PASS = "123456789";

    public static void main(String[] args) {
        Connection conn = null;
        Statement stmt = null;
        try{
            //Регистрация драйвера подключения к базе данных
            Class.forName(JDBC_DRIVER);

            //Открываем соединение
            System.out.println("Соединяемся с базой..");
            conn = DriverManager.getConnection(DB_URL,USER,PASS);

            //Создаем SQL оператор
            System.out.println("Создаем оператор...");
            stmt = conn.createStatement();
            String sql;
            sql = "SELECT id, chat_id, spend, date FROM spend";
            ResultSet rs = stmt.executeQuery(sql);

            //Обрабатываем ответ от базы данных
            while(rs.next()){
                //Получение данных из каждой колонки
                int id  = rs.getInt("id");
               Long chat_id = rs.getLong("chat_id");
                int spend = rs.getInt("spend");
                String date = rs.getString("date");

                //Display values
                System.out.print("ID: " + id);
                System.out.print(", chat_id: " + chat_id);
                System.out.print(", расход: " + spend);
                System.out.println(",дата: " + date);
            }
            //Завершаем работу с базой
            rs.close();
            stmt.close();
            conn.close();
        }catch(SQLException se){
            //Печатаем stacktrace, если что-то пошло не так
            se.printStackTrace();
        }catch(Exception e){
            //Если не удалось загрузить драйвер
            e.printStackTrace();
        }finally{
            //Закрываем все использованные ресурсы
            try{
                if(stmt!=null)
                    stmt.close();
            }catch(SQLException se2){
            }
            try{
                if(conn!=null)
                    conn.close();
            }catch(SQLException se){
                se.printStackTrace();
            }
        }
    }
}
