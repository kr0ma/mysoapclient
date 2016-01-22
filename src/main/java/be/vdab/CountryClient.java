package be.vdab;

import org.springframework.ws.client.core.support.WebServiceGatewaySupport;

import be.vdab.soapmessages.Country;
import be.vdab.soapmessages.GetCountryRequest;
import be.vdab.soapmessages.GetCountryResponse;

public class CountryClient extends WebServiceGatewaySupport  {
	  Country getCountry(String code) {
		    GetCountryRequest request = new GetCountryRequest();
		    request.setCode(code);
		    GetCountryResponse response = (GetCountryResponse) this.getWebServiceTemplate().marshalSendAndReceive(
		    this.getDefaultUri(), request);
		    return response.getCountry();
		  }
}
