package com.kbtg.hackathon.fruitmark;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.kbtg.hackathon.fruitmark.dao.MerchantRepository;
import com.kbtg.hackathon.fruitmark.entity.Merchant;

import static org.junit.Assert.*;

@SpringBootTest
/**
 * [...]
 * </ul>
 *
 * @since 4.5
 */
public class ConnectionData {
	
	@Autowired
    private MerchantRepository merchantRepository;
    /*@Before
    public void setUp() throws Exception {
        User user1= new User("Alice", 23);
        User user2= new User("Bob", 38);
        //save user, verify has ID value after save
        assertNull(user1.getId());
        assertNull(user2.getId());//null before save
        this.merchantRepository.save(user1);
        this.merchantRepository.save(user2);
        assertNotNull(user1.getId());
        assertNotNull(user2.getId());
    }*/
 
    @Test
    public void testFetchData(){
        /*Test data retrieval*/
        /*Merchant merchant = merchantRepository.findByName("Bob");
        assertNotNull(merchant);*/
        /*Get all products, list should only have two*/
        Iterable<Merchant> merchants = merchantRepository.findAll();
        int count = 0;
        for(Merchant p : merchants){
            count++;
        }
        assertEquals(count, 7);
    }

}
