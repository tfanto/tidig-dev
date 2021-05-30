package se.consid.applications.tidig.customer;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class CustomerDBTest {

    @Autowired
    private CustomerRepository customerRepository;

    @Test
    public void verifyRepositoryWorks() {

        Long ID = 9999l;

        Customer object = new Customer();
        object.setId(ID);
        object.setName("Kundnamn");
        customerRepository.save(object);
        Boolean exists = customerRepository.existsById(ID);
        assertTrue(exists);
        customerRepository.deleteById(ID);
        exists = customerRepository.existsById(ID);
        assertFalse(exists);
    }


}
