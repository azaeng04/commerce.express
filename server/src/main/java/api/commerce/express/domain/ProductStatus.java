package api.commerce.express.domain;

import jakarta.persistence.Embeddable;

import java.io.Serializable;

/**
 * @author Owner
 */
@Embeddable
public class ProductStatus implements Serializable {
    private String status;
    private Integer inStock;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getInStock() {
        return inStock;
    }

    public void setInStock(Integer inStock) {
        this.inStock = inStock;
    }
}
