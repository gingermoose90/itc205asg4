package hotel.checkout;

import static org.junit.jupiter.api.Assertions.*;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import hotel.credit.CreditCard;
import hotel.credit.CreditCardType;
import hotel.entities.Booking;
import hotel.entities.Guest;
import hotel.entities.Hotel;
import hotel.entities.Room;
import hotel.entities.RoomType;

class TestBugOne {
	
	Hotel hotel;
	Booking booking;
	Guest guest;
	Room room;
	CreditCard creditcard;
	CreditCardType cardType;
	CheckoutCTL control;
	
	Date arrivalDate;
	static SimpleDateFormat format;
	int occupantNumber;
	int stayLength;
	RoomType roomType;
	int roomId;
	long confirmationNumber;
	String guestName;
	String guestAddress;
	int phoneNumber;
	int ccv;
	
	static final int AUTHORISED_CARD_NUMBER = 3;
	

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		format = new SimpleDateFormat("dd-MM-yyyy");		
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@BeforeEach
	void setUp() throws Exception {
		hotel = new Hotel();
		control = new CheckoutCTL(hotel);
		
		arrivalDate = format.parse("01-01-2001");
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void testServiceChargeZero() {
		fail("Not yet implemented");
	}

}
