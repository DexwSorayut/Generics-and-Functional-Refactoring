import java.util.List;
import java.util.stream.Collectors;

public class ProductAnalytics {
    private List<Product> productCatalog;

    public ProductAnalytics(List<Product> productCatalog) {
        this.productCatalog = productCatalog;
    }

    // TODO: Refactor เมธอดทั้งหมดด้านล่างนี้ให้ใช้ Stream API

    /**
     * ค้นหาสินค้าทั้งหมดในหมวดหมู่ที่กำหนด
     */
    public List <Product> findProductsByCategory(String category) {
        return productCatalog.stream()
            .filter(p -> p.category().equals(category)) 
                //กรอง category ว่าเหมือนกับ category ที่รับมาหรือไม่
            .collect(Collectors.toList()); 
                //collect มีหน้าที่รวม category ลงใน List
    }

    /**
     * คืนค่า "ชื่อ" ของสินค้าทั้งหมดที่มีราคาต่ำกว่าที่กำหนด
     */
    public List<String> getProductNamesWithPriceLessThan(double maxPrice) {
        return productCatalog.stream()
            .filter(p -> p.price() < maxPrice)
                //กรองสินค้าราคาต่ำกว่าที่กำหนด 
            .map(p -> p.name())
                //ทำให้สินค้าเหลือแค่ชื่อ
            .collect(Collectors.toList());
                //รวบลงใน List
    }

    /**
     * คำนวณมูลค่ารวมของสต็อกสินค้าในหมวดหมู่ที่กำหนด
     */
    public double calculateTotalStockValueForCategory(String category) {
        return productCatalog.stream()
            .filter(p -> p.category().equals(category))
                //กรอง category ว่าเหมือนกับ category ที่รับมาหรือไม่
            .mapToDouble(p -> p.price() * p.stock())
                //เอาราคา * จำนวนสินค้าที่เหลืออยู่
            .sum();
                //คืนค่าเป็น double
    }

    /**
     * ตรวจสอบว่ามีสินค้าที่หมดสต็อก (stock = 0) หรือไม่
     */
    public boolean hasProductOutOfStock() {
        return productCatalog.stream()
            .filter(p -> p.stock() == 0)
                //กรองว่า stock = 0 หรือไม่
            .count() > 0;
                //ถ้ามี stock = 0 ให้นับ 1 ครั้ง ถ้านับแล้วมากกว่า 0 ให้ return true
    }
}
