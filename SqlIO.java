public class SqlIO extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "LudoGest";
    private static final int DATABASE_VERSION = 11;

    public SqlIO(Context context)
    {
        super( context, DATABASE_NAME, null, DATABASE_VERSION );
    }

    @Override
    public void onCreate(SQLiteDatabase db){
        db.beginTransaction();
        try{
            db.execSQL( "CREATE TABLE IF NOT EXISTS apuesta( "
                    + "id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, "
                    + "evento string(255), "
                    + "pronostico string(255), "
                    + "importe double, "
                    + "cuota douuble, "
                    + "ganancia double,"
                    + "resultado int)");

            db.setTransactionSuccessful();
        }finally {
            db.endTransaction();
        }
    }

    public void onUpgrade(SQLiteDatabase db,int oldVersion, int newVersion){
        Log.i(  "I-review.SqlIO",
                "Actualizando BBDD de version " + oldVersion + " a la " + newVersion );

        try {
            db.beginTransaction();
            db.execSQL( "DROP TABLE IF EXISTS apuesta");
            db.setTransactionSuccessful();
        } finally {
            db.endTransaction();
        }
        this.onCreate( db );
    }
}
