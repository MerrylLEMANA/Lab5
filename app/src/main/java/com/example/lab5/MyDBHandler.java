package com.example.lab5;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MyDBHandler extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "productDB.db";
    private static final String TABLE_PRODUCTS = "products";
    private static final String COLUMN_ID = "_id";
    private static final String COLUMN_PRODUCT_NAME = "productName";
    private static final String COLUMN_SKU = "SKU";

    public MyDBHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE = "CREATE TABLE " + TABLE_PRODUCTS + "("
                + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + COLUMN_PRODUCT_NAME + " TEXT,"
                + COLUMN_SKU + " INTEGER" + ")";
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PRODUCTS);
        onCreate(db);
    }

    public void addProduct(Product product) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_PRODUCT_NAME, product.getProductName());
        values.put(COLUMN_SKU, product.getSku());
        db.insert(TABLE_PRODUCTS, null, values);
        db.close();
    }

    public Product findProduct(String productName) {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM " + TABLE_PRODUCTS + " WHERE " + COLUMN_PRODUCT_NAME + " = \"" + productName + "\"";
        Cursor cursor = db.rawQuery(query, null);
        Product product = null;
        if (cursor.moveToFirst()) {
            product = new Product();
            product.setID(Integer.parseInt(cursor.getString(0)));
            product.setProductName(cursor.getString(1));
            product.setSku(Integer.parseInt(cursor.getString(2)));
        }
        cursor.close();
        db.close();
        return product;
    }

    public boolean deleteProduct(String productName) {
        SQLiteDatabase db = this.getWritableDatabase();
        int result = db.delete(TABLE_PRODUCTS, COLUMN_PRODUCT_NAME + " = ?", new String[]{productName});
        db.close();
        return result > 0;
    }

    public boolean updateProduct(Product product) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_PRODUCT_NAME, product.getProductName());
        values.put(COLUMN_SKU, product.getSku());
        int rows = db.update(TABLE_PRODUCTS, values, COLUMN_ID + " = ?", new String[]{String.valueOf(product.getId())});
        db.close();
        return rows > 0;
    }
}