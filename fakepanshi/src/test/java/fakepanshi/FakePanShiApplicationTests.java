package fakepanshi;

import com.mongodb.client.MongoCollection;
import org.bson.Document;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class FakePanShiApplicationTests {

    @Autowired
    private MongoTemplate template;

    @Test
    public void funt() {
        MongoCollection<Document> test = template.getCollection("test");
        System.out.println("test = " + test);

    }
}
