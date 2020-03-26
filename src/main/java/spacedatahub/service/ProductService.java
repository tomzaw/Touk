package spacedatahub.service;

import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.stereotype.Service;
import spacedatahub.model.Product;
import spacedatahub.repository.ProductRepository;
import spacedatahub.struct.ImageryType;

@Transactional
@Service
public class ProductService {

    private ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> findAll() {

        List<Product> products = productRepository.findAll();
        return products;
    }

    public Product find(Long id) {

        Product product = productRepository.findById(id).get();
        return product;
    }

    public List<Product> findAllByMissionName(String missionName) {

        List<Product> products = productRepository.findAllByMissionName(missionName).get();
        return products;
    }

    public List<Product> findAllByMissionImageryType(ImageryType imageryType) {

        List<Product> products = productRepository.findAllByMissionImageryType(imageryType).get();
        return products;
    }

    public List<Product> findAllByAcquisitionDateBefore(LocalDateTime datetime) {

        List<Product> products = productRepository.findAllByAcquisitionDateBefore(datetime).get();
        return products;
    }

    public List<Product> findAllByAcquisitionDateAfter(LocalDateTime datetime) {

        List<Product> products = productRepository.findAllByAcquisitionDateAfter(datetime).get();
        return products;
    }

    public List<Product> findAllByAcquisitionDateBetween(LocalDateTime datetime1, LocalDateTime datetime2) {

        List<Product> products = productRepository.findAllByAcquisitionDateBetween(datetime1, datetime2).get();
        return products;
    }

    public List<BigInteger> findMostOrderedIds(Long count) {

        List<BigInteger> productsIds = productRepository.findMostOrderedIds(count).get();
        return productsIds;
    }

    public List<String> findMostOrderedMissionNames(Long count) {

        List<String> productsIds = productRepository.findMostOrderedMissionNames(count).get();
        return productsIds;
    }

    public void save(Product product) {

        productRepository.save(product);
    }

    public void delete(Long id) {

        productRepository.deleteById(id);
    }
}
