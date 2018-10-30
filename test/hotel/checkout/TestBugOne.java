package hotel.checkout;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.junit.jupiter.MockitoExtension;


import hotel.checkin.CheckinCTL;
import hotel.checkin.CheckinUI;
import hotel.credit.CreditCard;
import hotel.credit.CreditCardType;
import hotel.entities.Booking;
import hotel.entities.Guest;
import hotel.entities.Hotel;
import hotel.entities.Room;
import hotel.entities.RoomType;
import hotel.entities.ServiceCharge;
import hotel.entities.ServiceType;
import hotel.service.RecordServiceCTL;
import hotel.service.RecordServiceUI;

@ExtendWith(MockitoExtension.class)
class TestBugOne {
	
	CheckinUI checkinUi;
	CheckoutUI checkoutUi;
	RecordServiceUI recordServiceUi;
	
	Hotel hotel;
	Booking booking;
	Guest guest;
	Room room;
	CreditCard creditCard;
	CreditCardType cardType;
	CheckinCTL checkInControl;
	CheckoutCTL checkOutControl;
	RecordServiceCTL recordServiceControl;
	ServiceCharge serviceCharge;
	ServiceType serviceType;
	
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
	double cost;
	
	static final int AUTHORISED_CARD_NUMBER = 3;
	

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		format = new SimpleDateFormat("dd-MM-yyyy");		
	}
	

	@BeforeEach
	void setUp() throws Exception {
		hotel = new Hotel();
		checkOutControl = new CheckoutCTL(hotel);
		checkOutControl.checkoutUI = new CheckoutUI(checkOutControl);
		
		checkInControl = new CheckinCTL(hotel);
		checkInControl.checkInUI = new CheckinUI(checkInControl);
		
		recordServiceControl = new RecordServiceCTL(hotel);
		recordServiceControl.recordServiceUI = new RecordServiceUI(recordServiceControl);
		
		arrivalDate = format.parse("01-01-2001");
		stayLength = 1;
		roomType = RoomType.SINGLE;
		roomId = 101;
		cardType = CreditCardType.VISA;
		guestName = "David";
		guestAddress = "123 Fake St";
		phoneNumber = 123456;
		serviceType = ServiceType.BAR_FRIDGE;
		cost = 15.00;
		
		creditCard = new CreditCard(cardType, AUTHORISED_CARD_NUMBER, ccv);
		
		checkInControl.hotel.addRoom(roomType, roomId);
		room = checkInControl.hotel.findAvailableRoom(roomType, arrivalDate, stayLength);
		guest = new Guest(guestName, guestAddress, phoneNumber);
		confirmationNumber = checkInControl.hotel.book(room, guest, arrivalDate, stayLength, occupantNumber, creditCard);
	}
	
	
	@Test
	void testServiceChargeError() {
		//arrange
		checkInControl.confirmationNumberEntered(confirmationNumber);
		checkInControl.checkInConfirmed(true);
		
		recordServiceControl.roomNumberEntered(roomId);
		recordServiceControl.serviceDetailsEntered(serviceType, cost);
		
		//act
		checkOutControl.state = CheckoutCTL.State.ROOM;
		checkOutControl.roomIdEntered(roomId);	
	}
}
