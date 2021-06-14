package operacionfuegoweb;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.rebelalliance.application.RestServiceApplication;
import com.rebelalliance.model.Position;
import com.rebelalliance.service.SateliteService;



@SpringBootTest	(classes=RestServiceApplication.class)
public class SateliteServiceTest {
	
	@Autowired
	SateliteService sateliteService;
	 @Test
	    public void getPositions() throws Exception {
	        double[] distances = new double[]{100.0, 200.0, 200.0};
	        Position p = new Position();
	        p.setX(-115.86519411857499);
	        p.setY(-85.31352976613795);
	        Position position = sateliteService.getLocation(distances);
	        assertEquals(p.getX(), position.getX());
	        assertEquals(p.getY(), position.getY());
	    }
	

}
