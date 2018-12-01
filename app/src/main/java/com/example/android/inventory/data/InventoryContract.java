package com.example.android.inventory.data;

import android.content.ContentResolver;
import android.net.Uri;
import android.provider.BaseColumns;

/**
 * API Contract for the Inventory app.
 */
public final class InventoryContract {

    /**
     * The "Content authority" is a name for the entire content provider, similar to the
     * relationship between a domain name and its website.  A convenient string to use for the
     * content authority is the package name for the app, which is guaranteed to be unique on the
     * device.
     */
    public static final String CONTENT_AUTHORITY = "com.example.android.inventory";

    /**
     * Use CONTENT_AUTHORITY to create the base of all URI's which apps will use to contact
     * the content provider.
     */
    public static final Uri BASE_CONTENT_URI = Uri.parse("content://" + CONTENT_AUTHORITY);

    /**
     * Possible path (appended to base content URI for possible URI's)
     * For instance, content://com.example.android.pets/pets/ is a valid path for
     * looking at Product data. content://com.example.android.pets/staff/ will fail,
     * as the ContentProvider hasn't been given any information on what to do with "staff".
     */
    public static final String PATH_PRODUCTS = "inventory";

    // To prevent someone from accidentally instantiating the contract class,
    // give it an empty constructor.
    private InventoryContract() {
    }

    /**
     * Inner class that defines constant values for the inventory database table.
     * Each entry in the table represents a single product.
     */
    public static class InventoryEntry implements BaseColumns {

        /**
         * The content URI to access the product data in the provider
         */
        public static final Uri CONTENT_URI = Uri.withAppendedPath(BASE_CONTENT_URI, PATH_PRODUCTS);

        /**
         * The MIME type of the {@link #CONTENT_URI} for a list of products.
         */
        public static final String CONTENT_LIST_TYPE =
                ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_PRODUCTS;

        /**
         * The MIME type of the {@link #CONTENT_URI} for a single product.
         */
        public static final String CONTENT_ITEM_TYPE =
                ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_PRODUCTS;

        /**
         * Name of database table for the inventory
         */
        public final static String TABLE_NAME = "inventory";

        /**
         * Unique ID number for the product (only for use in the database table).
         * <p>
         * Type: INTEGER
         */
        public final static String _ID = BaseColumns._ID;

        /**
         * Name of the product.
         * <p>
         * Type: TEXT
         */
        public static final String COLUMN_PRODUCT_NAME = "name";

        /**
         * Price of the product.
         * <p>
         * Type: INTEGER
         */
        public static final String COLUMN_PRICE = "price";

        /**
         * Quantity of the product.
         * <p>
         * Type: INTEGER
         */
        public static final String COLUMN_QUANTITY = "quantity";

        /**
         * Supplier's Name of the product.
         * <p>
         * Type: TEXT
         */
        public static final String COLUMN_SUPPLIER_NAME = "supplierName";

        /**
         * Supplier's Phone Number of the product.
         * <p>
         * Type: TEXT
         */
        public static final String COLUMN_SUPPLIER_PHONE_NUMBER = "supplierPhoneNumber";

        /**
         * Available order methods to get a product.
         * <p>
         * Type: TEXT
         */

        public static final String COLUMN_ORDER = "order_method";

        /**
         * Availability of the product.
         * <p>
         * Type: TEXT
         */
        public static final String COLUMN_STOCK = "stock";

        /**
         * Sales phone number.
         * <p>
         * Type: INTEGER
         */
        public static final String COLUMN_SALES = "sales_number";

        /**
         * Values for the stock of the product.
         */
        public static final int NO_AVAILABLE = 0;
        public static final int AVAILABLE = 1;

        /**
         * Returns whether or not the given availability is {@link #NO_AVAILABLE}, {@link #AVAILABLE}.
         */
        public static boolean isValidAvailability(int stock) {
            if (stock == NO_AVAILABLE || stock == AVAILABLE) {
                return true;
            }
            return false;
        }

        /**
         * Values for the method of order.
         */
        public static final int ORDER_PHONE = 0;
        public static final int ORDER_EMAIL = 1;
        public static final int ORDER_WEB = 2;

        /**
         * Returns whether or not the given method is.
         */
        public static boolean isValidOrder(int order) {
            if (order == ORDER_PHONE || order == ORDER_EMAIL || order == ORDER_WEB) {
                return true;
            }
            return false;
        }
    }
}
