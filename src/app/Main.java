package app;

import app.dao.imp.CustomerDao;
import app.dao.imp.ProductDao;
import app.dao.imp.SupplierDao;
import app.model.Customer;
import app.model.Product;
import app.model.Supplier;

import java.util.List;

public class Main {

    public static void main(String[] args) throws Exception {

        // dao
        CustomerDao customerDao = new CustomerDao(Common.DATABASE_URL, Common.DATABASE_DRIVER, Common.DATABASE_USERNAME, Common.DATABASE_PASSWORD);
        ProductDao productDao = new ProductDao(Common.DATABASE_URL, Common.DATABASE_DRIVER, Common.DATABASE_USERNAME, Common.DATABASE_PASSWORD);
        SupplierDao supplierDao = new SupplierDao(Common.DATABASE_URL, Common.DATABASE_DRIVER, Common.DATABASE_USERNAME, Common.DATABASE_PASSWORD);

        // create test customer
        Customer createTestCustomer = new Customer();
        createTestCustomer.setName("Bilge");
        createTestCustomer.setSurname("Kalan");
        createTestCustomer.setDate(Common.DATE_FORMATTER.parse("01/04/1993"));
        int customerId = customerDao.save(createTestCustomer);
        if (customerId != -1) {
            createTestCustomer.setId(customerId);
            System.out.println(createTestCustomer.toString());
        } else
            System.out.println("Insert Customer Error");

        // create test product
        Product createTestProduct = new Product();
        createTestProduct.setProductName("Kimono");
        createTestProduct.setQuantity(3500);
        createTestProduct.setPrice(3500f);
        int productId = productDao.save(createTestProduct);
        if (productId != -1) {
            createTestProduct.setProductId(productId);
            System.out.println(createTestProduct.toString());
        } else
            System.out.println("Insert Product Error");

        // create test supplier
        Supplier createTestSupplier = new Supplier();
        createTestSupplier.setName("Hikmet");
        createTestSupplier.setSurname("Alan");
        createTestSupplier.setDate(Common.DATE_FORMATTER.parse("26/12/2004"));
        int supplierId = supplierDao.save(createTestSupplier);
        if (supplierId != -1) {
            createTestSupplier.setSupplierId(supplierId);
            System.out.println(createTestSupplier.toString());
        } else
            System.out.println("Insert Supplier Error");

        // read test customer
        int readTestCustomerId = 13;
        Customer readTestCustomer = customerDao.get(readTestCustomerId);
        if (readTestCustomer != null)
            System.out.println(readTestCustomer.toString());
        else
            System.out.println("Read Customer Error");

        // read test product
        int readTestProductId = 1;
        Product readTestProduct = productDao.get(readTestProductId);
        if (readTestProduct != null)
            System.out.println(readTestProduct.toString());
        else
            System.out.println("Read Product Error");

        // read test supplier
        int readTestSupplierId = 1;
        Supplier readTestSupplier = supplierDao.get(readTestSupplierId);
        if (readTestSupplier != null)
            System.out.println(readTestSupplier.toString());
        else
            System.out.println("Read Supplier Error");

        // update test customer
        Customer updateTestCustomer = new Customer();
        updateTestCustomer.setId(13);
        updateTestCustomer.setName("Ozan");
        updateTestCustomer.setSurname("Kilinc");
        updateTestCustomer.setDate(Common.DATE_FORMATTER.parse("07/05/1963"));
        boolean updateCustomerResult = customerDao.update(updateTestCustomer);
        if (updateCustomerResult)
            System.out.println(updateTestCustomer.toString());
        else
            System.out.println("Update Customer Error");

        // update test product
        Product updateTestProduct = new Product();
        updateTestProduct.setProductId(12);
        updateTestProduct.setProductName("Gelinlik");
        updateTestProduct.setQuantity(3500);
        updateTestProduct.setPrice((float) 5.500);
        boolean updateProductResult = productDao.update(updateTestProduct);
        if (updateProductResult)
            System.out.println(updateTestProduct.toString());
        else
            System.out.println("Update Product Error");

        // update test supplier
        Supplier updateTestSupplier = new Supplier();
        updateTestSupplier.setSupplierId(1);
        updateTestSupplier.setName("Kemal");
        updateTestSupplier.setSurname("Elver");
        updateTestSupplier.setDate(Common.DATE_FORMATTER.parse("12/11/1999"));
        boolean updateSupplierResult = supplierDao.update(updateTestSupplier);
        if (updateSupplierResult)
            System.out.println(updateTestSupplier.toString());
        else
            System.out.println("Update Supplier Error");

        // delete test customer
        int deleteTestCustomerId = 65;
        boolean deleteCustomerResult = customerDao.delete(deleteTestCustomerId);
        if (deleteCustomerResult)
            System.out.println(updateTestCustomer.toString());
        else
            System.out.println("Delete Customer Error");

        // delete test product
        int deleteTestProductId = 1;
        boolean deleteProductResult = productDao.delete(deleteTestProductId);
        if (deleteProductResult)
            System.out.println(updateTestProduct.toString());
        else
            System.out.println("Delete Product Error");

        // delete test supplier
        int deleteTestSupplierId = 6;
        boolean deleteSupplierResult = supplierDao.delete(deleteTestSupplierId);
        if (deleteSupplierResult)
            System.out.println(updateTestSupplier.toString());
        else
            System.out.println("Delete Supplier Error");

        // read all test customer
        List<Customer> customers = customerDao.getAll();
        for (Customer temp : customers) {
            System.out.println(temp.toString());
        }

        // read all test product
        List<Product> products = productDao.getAll();
        for (Product tempProduct : products) {
            System.out.println(tempProduct.toString());
        }

        // read all test supplier
        List<Supplier> suppliers = supplierDao.getAll();
        for (Supplier tempSupplier : suppliers) {
            System.out.println(tempSupplier.toString());
        }

        customerDao.disconnect();
        productDao.disconnect();
        supplierDao.disconnect();
    }
}