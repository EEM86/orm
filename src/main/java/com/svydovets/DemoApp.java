package com.svydovets;

import com.svydovets.entity.Product;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Persistence;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class DemoApp {

  public static void main(String[] args) {
    var emf = Persistence.createEntityManagerFactory("default");
    final EntityManager em = emf.createEntityManager();

    final Product product = em.find(Product.class, 1);
    System.out.println(product);

    final Product myProduct = new Product();
    myProduct.setName("Pringles");
    myProduct.setPrice(BigDecimal.valueOf(81));
    myProduct.setCreatedAt(LocalDateTime.now());

    em.getTransaction().begin();
    em.persist(myProduct);
    em.getTransaction().commit();

    em.createQuery("select p from Product p where p.name = :name", Product.class)
        .setParameter("name", "Pringles")
        .getResultStream().forEach(System.out::println);

    em.close();
  }

}
