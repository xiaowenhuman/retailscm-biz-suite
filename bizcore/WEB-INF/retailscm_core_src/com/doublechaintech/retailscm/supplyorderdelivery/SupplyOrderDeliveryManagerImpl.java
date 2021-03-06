
package com.doublechaintech.retailscm.supplyorderdelivery;

import java.util.Date;
import java.util.Map;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.math.BigDecimal;
import com.terapico.caf.DateTime;
import com.doublechaintech.retailscm.BaseEntity;


import com.doublechaintech.retailscm.Message;
import com.doublechaintech.retailscm.SmartList;
import com.doublechaintech.retailscm.MultipleAccessKey;

import com.doublechaintech.retailscm.RetailscmUserContext;
//import com.doublechaintech.retailscm.BaseManagerImpl;
import com.doublechaintech.retailscm.RetailscmCheckerManager;
import com.doublechaintech.retailscm.CustomRetailscmCheckerManager;

import com.doublechaintech.retailscm.consumerorder.ConsumerOrder;
import com.doublechaintech.retailscm.supplyorder.SupplyOrder;


import com.doublechaintech.retailscm.supplyorderapproval.SupplyOrderApproval;
import com.doublechaintech.retailscm.retailstoremember.RetailStoreMember;
import com.doublechaintech.retailscm.retailstorecountrycenter.RetailStoreCountryCenter;
import com.doublechaintech.retailscm.goodssupplier.GoodsSupplier;
import com.doublechaintech.retailscm.supplyorderdelivery.SupplyOrderDelivery;
import com.doublechaintech.retailscm.retailstore.RetailStore;
import com.doublechaintech.retailscm.supplyorderpicking.SupplyOrderPicking;
import com.doublechaintech.retailscm.supplyorderprocessing.SupplyOrderProcessing;
import com.doublechaintech.retailscm.supplyorderconfirmation.SupplyOrderConfirmation;
import com.doublechaintech.retailscm.supplyordershipment.SupplyOrderShipment;






public class SupplyOrderDeliveryManagerImpl extends CustomRetailscmCheckerManager implements SupplyOrderDeliveryManager {
	
	private static final String SERVICE_TYPE = "SupplyOrderDelivery";
	@Override
	public SupplyOrderDeliveryDAO daoOf(RetailscmUserContext userContext) {
		return supplyOrderDeliveryDaoOf(userContext);
	}
	
	@Override
	public String serviceFor(){
		return SERVICE_TYPE;
	}
	
	
	protected void throwExceptionWithMessage(String value) throws SupplyOrderDeliveryManagerException{
	
		Message message = new Message();
		message.setBody(value);
		throw new SupplyOrderDeliveryManagerException(message);

	}
	
	

 	protected SupplyOrderDelivery saveSupplyOrderDelivery(RetailscmUserContext userContext, SupplyOrderDelivery supplyOrderDelivery, String [] tokensExpr) throws Exception{	
 		//return getSupplyOrderDeliveryDAO().save(supplyOrderDelivery, tokens);
 		
 		Map<String,Object>tokens = parseTokens(tokensExpr);
 		
 		return saveSupplyOrderDelivery(userContext, supplyOrderDelivery, tokens);
 	}
 	
 	protected SupplyOrderDelivery saveSupplyOrderDeliveryDetail(RetailscmUserContext userContext, SupplyOrderDelivery supplyOrderDelivery) throws Exception{	

 		
 		return saveSupplyOrderDelivery(userContext, supplyOrderDelivery, allTokens());
 	}
 	
 	public SupplyOrderDelivery loadSupplyOrderDelivery(RetailscmUserContext userContext, String supplyOrderDeliveryId, String [] tokensExpr) throws Exception{				
 
 		checkerOf(userContext).checkIdOfSupplyOrderDelivery(supplyOrderDeliveryId);
		checkerOf(userContext).throwExceptionIfHasErrors( SupplyOrderDeliveryManagerException.class);

 			
 		Map<String,Object>tokens = parseTokens(tokensExpr);
 		
 		SupplyOrderDelivery supplyOrderDelivery = loadSupplyOrderDelivery( userContext, supplyOrderDeliveryId, tokens);
 		//do some calc before sent to customer?
 		return present(userContext,supplyOrderDelivery, tokens);
 	}
 	
 	
 	 public SupplyOrderDelivery searchSupplyOrderDelivery(RetailscmUserContext userContext, String supplyOrderDeliveryId, String textToSearch,String [] tokensExpr) throws Exception{				
 
 		checkerOf(userContext).checkIdOfSupplyOrderDelivery(supplyOrderDeliveryId);
		checkerOf(userContext).throwExceptionIfHasErrors( SupplyOrderDeliveryManagerException.class);

 		
 		Map<String,Object>tokens = tokens().allTokens().searchEntireObjectText("startsWith", textToSearch).initWithArray(tokensExpr);
 		
 		SupplyOrderDelivery supplyOrderDelivery = loadSupplyOrderDelivery( userContext, supplyOrderDeliveryId, tokens);
 		//do some calc before sent to customer?
 		return present(userContext,supplyOrderDelivery, tokens);
 	}
 	
 	

 	protected SupplyOrderDelivery present(RetailscmUserContext userContext, SupplyOrderDelivery supplyOrderDelivery, Map<String, Object> tokens) throws Exception {
		
		
		addActions(userContext,supplyOrderDelivery,tokens);
		
		
		SupplyOrderDelivery  supplyOrderDeliveryToPresent = supplyOrderDeliveryDaoOf(userContext).present(supplyOrderDelivery, tokens);
		
		List<BaseEntity> entityListToNaming = supplyOrderDeliveryToPresent.collectRefercencesFromLists();
		supplyOrderDeliveryDaoOf(userContext).alias(entityListToNaming);
		
		return  supplyOrderDeliveryToPresent;
		
		
	}
 
 	
 	
 	public SupplyOrderDelivery loadSupplyOrderDeliveryDetail(RetailscmUserContext userContext, String supplyOrderDeliveryId) throws Exception{	
 		SupplyOrderDelivery supplyOrderDelivery = loadSupplyOrderDelivery( userContext, supplyOrderDeliveryId, allTokens());
 		return present(userContext,supplyOrderDelivery, allTokens());
		
 	}
 	
 	public Object view(RetailscmUserContext userContext, String supplyOrderDeliveryId) throws Exception{	
 		SupplyOrderDelivery supplyOrderDelivery = loadSupplyOrderDelivery( userContext, supplyOrderDeliveryId, viewTokens());
 		return present(userContext,supplyOrderDelivery, allTokens());
		
 	}
 	protected SupplyOrderDelivery saveSupplyOrderDelivery(RetailscmUserContext userContext, SupplyOrderDelivery supplyOrderDelivery, Map<String,Object>tokens) throws Exception{	
 		return supplyOrderDeliveryDaoOf(userContext).save(supplyOrderDelivery, tokens);
 	}
 	protected SupplyOrderDelivery loadSupplyOrderDelivery(RetailscmUserContext userContext, String supplyOrderDeliveryId, Map<String,Object>tokens) throws Exception{	
		checkerOf(userContext).checkIdOfSupplyOrderDelivery(supplyOrderDeliveryId);
		checkerOf(userContext).throwExceptionIfHasErrors( SupplyOrderDeliveryManagerException.class);

 
 		return supplyOrderDeliveryDaoOf(userContext).load(supplyOrderDeliveryId, tokens);
 	}

	


 	


 	
 	
 	protected<T extends BaseEntity> void addActions(RetailscmUserContext userContext, SupplyOrderDelivery supplyOrderDelivery, Map<String, Object> tokens){
		super.addActions(userContext, supplyOrderDelivery, tokens);
		
		addAction(userContext, supplyOrderDelivery, tokens,"@create","createSupplyOrderDelivery","createSupplyOrderDelivery/","main","primary");
		addAction(userContext, supplyOrderDelivery, tokens,"@update","updateSupplyOrderDelivery","updateSupplyOrderDelivery/"+supplyOrderDelivery.getId()+"/","main","primary");
		addAction(userContext, supplyOrderDelivery, tokens,"@copy","cloneSupplyOrderDelivery","cloneSupplyOrderDelivery/"+supplyOrderDelivery.getId()+"/","main","primary");
		
		addAction(userContext, supplyOrderDelivery, tokens,"supply_order_delivery.addConsumerOrder","addConsumerOrder","addConsumerOrder/"+supplyOrderDelivery.getId()+"/","consumerOrderList","primary");
		addAction(userContext, supplyOrderDelivery, tokens,"supply_order_delivery.removeConsumerOrder","removeConsumerOrder","removeConsumerOrder/"+supplyOrderDelivery.getId()+"/","consumerOrderList","primary");
		addAction(userContext, supplyOrderDelivery, tokens,"supply_order_delivery.updateConsumerOrder","updateConsumerOrder","updateConsumerOrder/"+supplyOrderDelivery.getId()+"/","consumerOrderList","primary");
		addAction(userContext, supplyOrderDelivery, tokens,"supply_order_delivery.copyConsumerOrderFrom","copyConsumerOrderFrom","copyConsumerOrderFrom/"+supplyOrderDelivery.getId()+"/","consumerOrderList","primary");
		addAction(userContext, supplyOrderDelivery, tokens,"supply_order_delivery.addSupplyOrder","addSupplyOrder","addSupplyOrder/"+supplyOrderDelivery.getId()+"/","supplyOrderList","primary");
		addAction(userContext, supplyOrderDelivery, tokens,"supply_order_delivery.removeSupplyOrder","removeSupplyOrder","removeSupplyOrder/"+supplyOrderDelivery.getId()+"/","supplyOrderList","primary");
		addAction(userContext, supplyOrderDelivery, tokens,"supply_order_delivery.updateSupplyOrder","updateSupplyOrder","updateSupplyOrder/"+supplyOrderDelivery.getId()+"/","supplyOrderList","primary");
		addAction(userContext, supplyOrderDelivery, tokens,"supply_order_delivery.copySupplyOrderFrom","copySupplyOrderFrom","copySupplyOrderFrom/"+supplyOrderDelivery.getId()+"/","supplyOrderList","primary");
	
		
		
	}// end method of protected<T extends BaseEntity> void addActions(RetailscmUserContext userContext, SupplyOrderDelivery supplyOrderDelivery, Map<String, Object> tokens){
	
 	
 	
 
 	
 	

	public SupplyOrderDelivery createSupplyOrderDelivery(RetailscmUserContext userContext, String who,Date deliveryTime) throws Exception
	//public SupplyOrderDelivery createSupplyOrderDelivery(RetailscmUserContext userContext,String who, Date deliveryTime) throws Exception
	{
		
		

		

		checkerOf(userContext).checkWhoOfSupplyOrderDelivery(who);
		checkerOf(userContext).checkDeliveryTimeOfSupplyOrderDelivery(deliveryTime);
	
		checkerOf(userContext).throwExceptionIfHasErrors(SupplyOrderDeliveryManagerException.class);


		SupplyOrderDelivery supplyOrderDelivery=createNewSupplyOrderDelivery();	

		supplyOrderDelivery.setWho(who);
		supplyOrderDelivery.setDeliveryTime(deliveryTime);

		supplyOrderDelivery = saveSupplyOrderDelivery(userContext, supplyOrderDelivery, emptyOptions());
		
		onNewInstanceCreated(userContext, supplyOrderDelivery);
		return supplyOrderDelivery;

		
	}
	protected SupplyOrderDelivery createNewSupplyOrderDelivery() 
	{
		
		return new SupplyOrderDelivery();		
	}
	
	protected void checkParamsForUpdatingSupplyOrderDelivery(RetailscmUserContext userContext,String supplyOrderDeliveryId, int supplyOrderDeliveryVersion, String property, String newValueExpr,String [] tokensExpr)throws Exception
	{
		

		
		
		checkerOf(userContext).checkIdOfSupplyOrderDelivery(supplyOrderDeliveryId);
		checkerOf(userContext).checkVersionOfSupplyOrderDelivery( supplyOrderDeliveryVersion);
		

		if(SupplyOrderDelivery.WHO_PROPERTY.equals(property)){
			checkerOf(userContext).checkWhoOfSupplyOrderDelivery(parseString(newValueExpr));
		}
		if(SupplyOrderDelivery.DELIVERY_TIME_PROPERTY.equals(property)){
			checkerOf(userContext).checkDeliveryTimeOfSupplyOrderDelivery(parseDate(newValueExpr));
		}
	
		checkerOf(userContext).throwExceptionIfHasErrors(SupplyOrderDeliveryManagerException.class);
	
		
	}
	
	
	
	public SupplyOrderDelivery clone(RetailscmUserContext userContext, String fromSupplyOrderDeliveryId) throws Exception{
		
		return supplyOrderDeliveryDaoOf(userContext).clone(fromSupplyOrderDeliveryId, this.allTokens());
	}
	
	public SupplyOrderDelivery internalSaveSupplyOrderDelivery(RetailscmUserContext userContext, SupplyOrderDelivery supplyOrderDelivery) throws Exception 
	{
		return internalSaveSupplyOrderDelivery(userContext, supplyOrderDelivery, allTokens());

	}
	public SupplyOrderDelivery internalSaveSupplyOrderDelivery(RetailscmUserContext userContext, SupplyOrderDelivery supplyOrderDelivery, Map<String,Object> options) throws Exception 
	{
		//checkParamsForUpdatingSupplyOrderDelivery(userContext, supplyOrderDeliveryId, supplyOrderDeliveryVersion, property, newValueExpr, tokensExpr);
		
		
		synchronized(supplyOrderDelivery){ 
			//will be good when the supplyOrderDelivery loaded from this JVM process cache.
			//also good when there is a ram based DAO implementation
			//make changes to SupplyOrderDelivery.
			if (supplyOrderDelivery.isChanged()){
			
			}
			supplyOrderDelivery = saveSupplyOrderDelivery(userContext, supplyOrderDelivery, options);
			return supplyOrderDelivery;
			
		}

	}
	
	public SupplyOrderDelivery updateSupplyOrderDelivery(RetailscmUserContext userContext,String supplyOrderDeliveryId, int supplyOrderDeliveryVersion, String property, String newValueExpr,String [] tokensExpr) throws Exception 
	{
		checkParamsForUpdatingSupplyOrderDelivery(userContext, supplyOrderDeliveryId, supplyOrderDeliveryVersion, property, newValueExpr, tokensExpr);
		
		
		
		SupplyOrderDelivery supplyOrderDelivery = loadSupplyOrderDelivery(userContext, supplyOrderDeliveryId, allTokens());
		if(supplyOrderDelivery.getVersion() != supplyOrderDeliveryVersion){
			String message = "The target version("+supplyOrderDelivery.getVersion()+") is not equals to version("+supplyOrderDeliveryVersion+") provided";
			throwExceptionWithMessage(message);
		}
		synchronized(supplyOrderDelivery){ 
			//will be good when the supplyOrderDelivery loaded from this JVM process cache.
			//also good when there is a ram based DAO implementation
			//make changes to SupplyOrderDelivery.
			
			supplyOrderDelivery.changeProperty(property, newValueExpr);
			supplyOrderDelivery = saveSupplyOrderDelivery(userContext, supplyOrderDelivery, tokens().done());
			return present(userContext,supplyOrderDelivery, mergedAllTokens(tokensExpr));
			//return saveSupplyOrderDelivery(userContext, supplyOrderDelivery, tokens().done());
		}

	}
	
	public SupplyOrderDelivery updateSupplyOrderDeliveryProperty(RetailscmUserContext userContext,String supplyOrderDeliveryId, int supplyOrderDeliveryVersion, String property, String newValueExpr,String [] tokensExpr) throws Exception 
	{
		checkParamsForUpdatingSupplyOrderDelivery(userContext, supplyOrderDeliveryId, supplyOrderDeliveryVersion, property, newValueExpr, tokensExpr);
		
		SupplyOrderDelivery supplyOrderDelivery = loadSupplyOrderDelivery(userContext, supplyOrderDeliveryId, allTokens());
		if(supplyOrderDelivery.getVersion() != supplyOrderDeliveryVersion){
			String message = "The target version("+supplyOrderDelivery.getVersion()+") is not equals to version("+supplyOrderDeliveryVersion+") provided";
			throwExceptionWithMessage(message);
		}
		synchronized(supplyOrderDelivery){ 
			//will be good when the supplyOrderDelivery loaded from this JVM process cache.
			//also good when there is a ram based DAO implementation
			//make changes to SupplyOrderDelivery.
			
			supplyOrderDelivery.changeProperty(property, newValueExpr);
			
			supplyOrderDelivery = saveSupplyOrderDelivery(userContext, supplyOrderDelivery, tokens().done());
			return present(userContext,supplyOrderDelivery, mergedAllTokens(tokensExpr));
			//return saveSupplyOrderDelivery(userContext, supplyOrderDelivery, tokens().done());
		}

	}
	protected Map<String,Object> emptyOptions(){
		return tokens().done();
	}
	
	protected SupplyOrderDeliveryTokens tokens(){
		return SupplyOrderDeliveryTokens.start();
	}
	protected Map<String,Object> parseTokens(String [] tokensExpr){
		return tokens().initWithArray(tokensExpr);
	}
	protected Map<String,Object> allTokens(){
		return SupplyOrderDeliveryTokens.all();
	}
	protected Map<String,Object> viewTokens(){
		return tokens().allTokens()
		.sortConsumerOrderListWith("id","desc")
		.sortSupplyOrderListWith("id","desc")
		.analyzeAllLists().done();

	}
	protected Map<String,Object> mergedAllTokens(String []tokens){
		return SupplyOrderDeliveryTokens.mergeAll(tokens).done();
	}
	
//--------------------------------------------------------------
	
	//--------------------------------------------------------------

	public void delete(RetailscmUserContext userContext, String supplyOrderDeliveryId, int supplyOrderDeliveryVersion) throws Exception {
		//deleteInternal(userContext, supplyOrderDeliveryId, supplyOrderDeliveryVersion);		
	}
	protected void deleteInternal(RetailscmUserContext userContext,
			String supplyOrderDeliveryId, int supplyOrderDeliveryVersion) throws Exception{
			
		supplyOrderDeliveryDaoOf(userContext).delete(supplyOrderDeliveryId, supplyOrderDeliveryVersion);
	}
	
	public SupplyOrderDelivery forgetByAll(RetailscmUserContext userContext, String supplyOrderDeliveryId, int supplyOrderDeliveryVersion) throws Exception {
		return forgetByAllInternal(userContext, supplyOrderDeliveryId, supplyOrderDeliveryVersion);		
	}
	protected SupplyOrderDelivery forgetByAllInternal(RetailscmUserContext userContext,
			String supplyOrderDeliveryId, int supplyOrderDeliveryVersion) throws Exception{
			
		return supplyOrderDeliveryDaoOf(userContext).disconnectFromAll(supplyOrderDeliveryId, supplyOrderDeliveryVersion);
	}
	
	

	
	public int deleteAll(RetailscmUserContext userContext, String secureCode) throws Exception
	{
		/*
		if(!("dElEtEaLl".equals(secureCode))){
			throw new SupplyOrderDeliveryManagerException("Your secure code is not right, please guess again");
		}
		return deleteAllInternal(userContext);
		*/
		return 0;
	}
	
	
	protected int deleteAllInternal(RetailscmUserContext userContext) throws Exception{
		return supplyOrderDeliveryDaoOf(userContext).deleteAll();
	}


	//disconnect SupplyOrderDelivery with consumer in ConsumerOrder
	protected SupplyOrderDelivery breakWithConsumerOrderByConsumer(RetailscmUserContext userContext, String supplyOrderDeliveryId, String consumerId,  String [] tokensExpr)
		 throws Exception{
			
			//TODO add check code here
			
			SupplyOrderDelivery supplyOrderDelivery = loadSupplyOrderDelivery(userContext, supplyOrderDeliveryId, allTokens());

			synchronized(supplyOrderDelivery){ 
				//Will be good when the thread loaded from this JVM process cache.
				//Also good when there is a RAM based DAO implementation
				
				supplyOrderDeliveryDaoOf(userContext).planToRemoveConsumerOrderListWithConsumer(supplyOrderDelivery, consumerId, this.emptyOptions());

				supplyOrderDelivery = saveSupplyOrderDelivery(userContext, supplyOrderDelivery, tokens().withConsumerOrderList().done());
				return supplyOrderDelivery;
			}
	}
	//disconnect SupplyOrderDelivery with confirmation in ConsumerOrder
	protected SupplyOrderDelivery breakWithConsumerOrderByConfirmation(RetailscmUserContext userContext, String supplyOrderDeliveryId, String confirmationId,  String [] tokensExpr)
		 throws Exception{
			
			//TODO add check code here
			
			SupplyOrderDelivery supplyOrderDelivery = loadSupplyOrderDelivery(userContext, supplyOrderDeliveryId, allTokens());

			synchronized(supplyOrderDelivery){ 
				//Will be good when the thread loaded from this JVM process cache.
				//Also good when there is a RAM based DAO implementation
				
				supplyOrderDeliveryDaoOf(userContext).planToRemoveConsumerOrderListWithConfirmation(supplyOrderDelivery, confirmationId, this.emptyOptions());

				supplyOrderDelivery = saveSupplyOrderDelivery(userContext, supplyOrderDelivery, tokens().withConsumerOrderList().done());
				return supplyOrderDelivery;
			}
	}
	//disconnect SupplyOrderDelivery with approval in ConsumerOrder
	protected SupplyOrderDelivery breakWithConsumerOrderByApproval(RetailscmUserContext userContext, String supplyOrderDeliveryId, String approvalId,  String [] tokensExpr)
		 throws Exception{
			
			//TODO add check code here
			
			SupplyOrderDelivery supplyOrderDelivery = loadSupplyOrderDelivery(userContext, supplyOrderDeliveryId, allTokens());

			synchronized(supplyOrderDelivery){ 
				//Will be good when the thread loaded from this JVM process cache.
				//Also good when there is a RAM based DAO implementation
				
				supplyOrderDeliveryDaoOf(userContext).planToRemoveConsumerOrderListWithApproval(supplyOrderDelivery, approvalId, this.emptyOptions());

				supplyOrderDelivery = saveSupplyOrderDelivery(userContext, supplyOrderDelivery, tokens().withConsumerOrderList().done());
				return supplyOrderDelivery;
			}
	}
	//disconnect SupplyOrderDelivery with processing in ConsumerOrder
	protected SupplyOrderDelivery breakWithConsumerOrderByProcessing(RetailscmUserContext userContext, String supplyOrderDeliveryId, String processingId,  String [] tokensExpr)
		 throws Exception{
			
			//TODO add check code here
			
			SupplyOrderDelivery supplyOrderDelivery = loadSupplyOrderDelivery(userContext, supplyOrderDeliveryId, allTokens());

			synchronized(supplyOrderDelivery){ 
				//Will be good when the thread loaded from this JVM process cache.
				//Also good when there is a RAM based DAO implementation
				
				supplyOrderDeliveryDaoOf(userContext).planToRemoveConsumerOrderListWithProcessing(supplyOrderDelivery, processingId, this.emptyOptions());

				supplyOrderDelivery = saveSupplyOrderDelivery(userContext, supplyOrderDelivery, tokens().withConsumerOrderList().done());
				return supplyOrderDelivery;
			}
	}
	//disconnect SupplyOrderDelivery with shipment in ConsumerOrder
	protected SupplyOrderDelivery breakWithConsumerOrderByShipment(RetailscmUserContext userContext, String supplyOrderDeliveryId, String shipmentId,  String [] tokensExpr)
		 throws Exception{
			
			//TODO add check code here
			
			SupplyOrderDelivery supplyOrderDelivery = loadSupplyOrderDelivery(userContext, supplyOrderDeliveryId, allTokens());

			synchronized(supplyOrderDelivery){ 
				//Will be good when the thread loaded from this JVM process cache.
				//Also good when there is a RAM based DAO implementation
				
				supplyOrderDeliveryDaoOf(userContext).planToRemoveConsumerOrderListWithShipment(supplyOrderDelivery, shipmentId, this.emptyOptions());

				supplyOrderDelivery = saveSupplyOrderDelivery(userContext, supplyOrderDelivery, tokens().withConsumerOrderList().done());
				return supplyOrderDelivery;
			}
	}
	//disconnect SupplyOrderDelivery with store in ConsumerOrder
	protected SupplyOrderDelivery breakWithConsumerOrderByStore(RetailscmUserContext userContext, String supplyOrderDeliveryId, String storeId,  String [] tokensExpr)
		 throws Exception{
			
			//TODO add check code here
			
			SupplyOrderDelivery supplyOrderDelivery = loadSupplyOrderDelivery(userContext, supplyOrderDeliveryId, allTokens());

			synchronized(supplyOrderDelivery){ 
				//Will be good when the thread loaded from this JVM process cache.
				//Also good when there is a RAM based DAO implementation
				
				supplyOrderDeliveryDaoOf(userContext).planToRemoveConsumerOrderListWithStore(supplyOrderDelivery, storeId, this.emptyOptions());

				supplyOrderDelivery = saveSupplyOrderDelivery(userContext, supplyOrderDelivery, tokens().withConsumerOrderList().done());
				return supplyOrderDelivery;
			}
	}
	//disconnect SupplyOrderDelivery with buyer in SupplyOrder
	protected SupplyOrderDelivery breakWithSupplyOrderByBuyer(RetailscmUserContext userContext, String supplyOrderDeliveryId, String buyerId,  String [] tokensExpr)
		 throws Exception{
			
			//TODO add check code here
			
			SupplyOrderDelivery supplyOrderDelivery = loadSupplyOrderDelivery(userContext, supplyOrderDeliveryId, allTokens());

			synchronized(supplyOrderDelivery){ 
				//Will be good when the thread loaded from this JVM process cache.
				//Also good when there is a RAM based DAO implementation
				
				supplyOrderDeliveryDaoOf(userContext).planToRemoveSupplyOrderListWithBuyer(supplyOrderDelivery, buyerId, this.emptyOptions());

				supplyOrderDelivery = saveSupplyOrderDelivery(userContext, supplyOrderDelivery, tokens().withSupplyOrderList().done());
				return supplyOrderDelivery;
			}
	}
	//disconnect SupplyOrderDelivery with seller in SupplyOrder
	protected SupplyOrderDelivery breakWithSupplyOrderBySeller(RetailscmUserContext userContext, String supplyOrderDeliveryId, String sellerId,  String [] tokensExpr)
		 throws Exception{
			
			//TODO add check code here
			
			SupplyOrderDelivery supplyOrderDelivery = loadSupplyOrderDelivery(userContext, supplyOrderDeliveryId, allTokens());

			synchronized(supplyOrderDelivery){ 
				//Will be good when the thread loaded from this JVM process cache.
				//Also good when there is a RAM based DAO implementation
				
				supplyOrderDeliveryDaoOf(userContext).planToRemoveSupplyOrderListWithSeller(supplyOrderDelivery, sellerId, this.emptyOptions());

				supplyOrderDelivery = saveSupplyOrderDelivery(userContext, supplyOrderDelivery, tokens().withSupplyOrderList().done());
				return supplyOrderDelivery;
			}
	}
	//disconnect SupplyOrderDelivery with confirmation in SupplyOrder
	protected SupplyOrderDelivery breakWithSupplyOrderByConfirmation(RetailscmUserContext userContext, String supplyOrderDeliveryId, String confirmationId,  String [] tokensExpr)
		 throws Exception{
			
			//TODO add check code here
			
			SupplyOrderDelivery supplyOrderDelivery = loadSupplyOrderDelivery(userContext, supplyOrderDeliveryId, allTokens());

			synchronized(supplyOrderDelivery){ 
				//Will be good when the thread loaded from this JVM process cache.
				//Also good when there is a RAM based DAO implementation
				
				supplyOrderDeliveryDaoOf(userContext).planToRemoveSupplyOrderListWithConfirmation(supplyOrderDelivery, confirmationId, this.emptyOptions());

				supplyOrderDelivery = saveSupplyOrderDelivery(userContext, supplyOrderDelivery, tokens().withSupplyOrderList().done());
				return supplyOrderDelivery;
			}
	}
	//disconnect SupplyOrderDelivery with approval in SupplyOrder
	protected SupplyOrderDelivery breakWithSupplyOrderByApproval(RetailscmUserContext userContext, String supplyOrderDeliveryId, String approvalId,  String [] tokensExpr)
		 throws Exception{
			
			//TODO add check code here
			
			SupplyOrderDelivery supplyOrderDelivery = loadSupplyOrderDelivery(userContext, supplyOrderDeliveryId, allTokens());

			synchronized(supplyOrderDelivery){ 
				//Will be good when the thread loaded from this JVM process cache.
				//Also good when there is a RAM based DAO implementation
				
				supplyOrderDeliveryDaoOf(userContext).planToRemoveSupplyOrderListWithApproval(supplyOrderDelivery, approvalId, this.emptyOptions());

				supplyOrderDelivery = saveSupplyOrderDelivery(userContext, supplyOrderDelivery, tokens().withSupplyOrderList().done());
				return supplyOrderDelivery;
			}
	}
	//disconnect SupplyOrderDelivery with processing in SupplyOrder
	protected SupplyOrderDelivery breakWithSupplyOrderByProcessing(RetailscmUserContext userContext, String supplyOrderDeliveryId, String processingId,  String [] tokensExpr)
		 throws Exception{
			
			//TODO add check code here
			
			SupplyOrderDelivery supplyOrderDelivery = loadSupplyOrderDelivery(userContext, supplyOrderDeliveryId, allTokens());

			synchronized(supplyOrderDelivery){ 
				//Will be good when the thread loaded from this JVM process cache.
				//Also good when there is a RAM based DAO implementation
				
				supplyOrderDeliveryDaoOf(userContext).planToRemoveSupplyOrderListWithProcessing(supplyOrderDelivery, processingId, this.emptyOptions());

				supplyOrderDelivery = saveSupplyOrderDelivery(userContext, supplyOrderDelivery, tokens().withSupplyOrderList().done());
				return supplyOrderDelivery;
			}
	}
	//disconnect SupplyOrderDelivery with picking in SupplyOrder
	protected SupplyOrderDelivery breakWithSupplyOrderByPicking(RetailscmUserContext userContext, String supplyOrderDeliveryId, String pickingId,  String [] tokensExpr)
		 throws Exception{
			
			//TODO add check code here
			
			SupplyOrderDelivery supplyOrderDelivery = loadSupplyOrderDelivery(userContext, supplyOrderDeliveryId, allTokens());

			synchronized(supplyOrderDelivery){ 
				//Will be good when the thread loaded from this JVM process cache.
				//Also good when there is a RAM based DAO implementation
				
				supplyOrderDeliveryDaoOf(userContext).planToRemoveSupplyOrderListWithPicking(supplyOrderDelivery, pickingId, this.emptyOptions());

				supplyOrderDelivery = saveSupplyOrderDelivery(userContext, supplyOrderDelivery, tokens().withSupplyOrderList().done());
				return supplyOrderDelivery;
			}
	}
	//disconnect SupplyOrderDelivery with shipment in SupplyOrder
	protected SupplyOrderDelivery breakWithSupplyOrderByShipment(RetailscmUserContext userContext, String supplyOrderDeliveryId, String shipmentId,  String [] tokensExpr)
		 throws Exception{
			
			//TODO add check code here
			
			SupplyOrderDelivery supplyOrderDelivery = loadSupplyOrderDelivery(userContext, supplyOrderDeliveryId, allTokens());

			synchronized(supplyOrderDelivery){ 
				//Will be good when the thread loaded from this JVM process cache.
				//Also good when there is a RAM based DAO implementation
				
				supplyOrderDeliveryDaoOf(userContext).planToRemoveSupplyOrderListWithShipment(supplyOrderDelivery, shipmentId, this.emptyOptions());

				supplyOrderDelivery = saveSupplyOrderDelivery(userContext, supplyOrderDelivery, tokens().withSupplyOrderList().done());
				return supplyOrderDelivery;
			}
	}
	
	
	
	
	

	protected void checkParamsForAddingConsumerOrder(RetailscmUserContext userContext, String supplyOrderDeliveryId, String title, String consumerId, String confirmationId, String approvalId, String processingId, String shipmentId, String storeId,String [] tokensExpr) throws Exception{
		
				checkerOf(userContext).checkIdOfSupplyOrderDelivery(supplyOrderDeliveryId);

		
		checkerOf(userContext).checkTitleOfConsumerOrder(title);
		
		checkerOf(userContext).checkConsumerIdOfConsumerOrder(consumerId);
		
		checkerOf(userContext).checkConfirmationIdOfConsumerOrder(confirmationId);
		
		checkerOf(userContext).checkApprovalIdOfConsumerOrder(approvalId);
		
		checkerOf(userContext).checkProcessingIdOfConsumerOrder(processingId);
		
		checkerOf(userContext).checkShipmentIdOfConsumerOrder(shipmentId);
		
		checkerOf(userContext).checkStoreIdOfConsumerOrder(storeId);
	
		checkerOf(userContext).throwExceptionIfHasErrors(SupplyOrderDeliveryManagerException.class);

	
	}
	public  SupplyOrderDelivery addConsumerOrder(RetailscmUserContext userContext, String supplyOrderDeliveryId, String title, String consumerId, String confirmationId, String approvalId, String processingId, String shipmentId, String storeId, String [] tokensExpr) throws Exception
	{	
		
		checkParamsForAddingConsumerOrder(userContext,supplyOrderDeliveryId,title, consumerId, confirmationId, approvalId, processingId, shipmentId, storeId,tokensExpr);
		
		ConsumerOrder consumerOrder = createConsumerOrder(userContext,title, consumerId, confirmationId, approvalId, processingId, shipmentId, storeId);
		
		SupplyOrderDelivery supplyOrderDelivery = loadSupplyOrderDelivery(userContext, supplyOrderDeliveryId, allTokens());
		synchronized(supplyOrderDelivery){ 
			//Will be good when the supplyOrderDelivery loaded from this JVM process cache.
			//Also good when there is a RAM based DAO implementation
			supplyOrderDelivery.addConsumerOrder( consumerOrder );		
			supplyOrderDelivery = saveSupplyOrderDelivery(userContext, supplyOrderDelivery, tokens().withConsumerOrderList().done());
			
			userContext.getManagerGroup().getConsumerOrderManager().onNewInstanceCreated(userContext, consumerOrder);
			return present(userContext,supplyOrderDelivery, mergedAllTokens(tokensExpr));
		}
	}
	protected void checkParamsForUpdatingConsumerOrderProperties(RetailscmUserContext userContext, String supplyOrderDeliveryId,String id,String title,String [] tokensExpr) throws Exception {
		
		checkerOf(userContext).checkIdOfSupplyOrderDelivery(supplyOrderDeliveryId);
		checkerOf(userContext).checkIdOfConsumerOrder(id);
		
		checkerOf(userContext).checkTitleOfConsumerOrder( title);

		checkerOf(userContext).throwExceptionIfHasErrors(SupplyOrderDeliveryManagerException.class);
		
	}
	public  SupplyOrderDelivery updateConsumerOrderProperties(RetailscmUserContext userContext, String supplyOrderDeliveryId, String id,String title, String [] tokensExpr) throws Exception
	{	
		checkParamsForUpdatingConsumerOrderProperties(userContext,supplyOrderDeliveryId,id,title,tokensExpr);

		Map<String, Object> options = tokens()
				.allTokens()
				//.withConsumerOrderListList()
				.searchConsumerOrderListWith(ConsumerOrder.ID_PROPERTY, "is", id).done();
		
		SupplyOrderDelivery supplyOrderDeliveryToUpdate = loadSupplyOrderDelivery(userContext, supplyOrderDeliveryId, options);
		
		if(supplyOrderDeliveryToUpdate.getConsumerOrderList().isEmpty()){
			throw new SupplyOrderDeliveryManagerException("ConsumerOrder is NOT FOUND with id: '"+id+"'");
		}
		
		ConsumerOrder item = supplyOrderDeliveryToUpdate.getConsumerOrderList().first();
		
		item.updateTitle( title );

		
		//checkParamsForAddingConsumerOrder(userContext,supplyOrderDeliveryId,name, code, used,tokensExpr);
		SupplyOrderDelivery supplyOrderDelivery = saveSupplyOrderDelivery(userContext, supplyOrderDeliveryToUpdate, tokens().withConsumerOrderList().done());
		synchronized(supplyOrderDelivery){ 
			return present(userContext,supplyOrderDelivery, mergedAllTokens(tokensExpr));
		}
	}
	
	
	protected ConsumerOrder createConsumerOrder(RetailscmUserContext userContext, String title, String consumerId, String confirmationId, String approvalId, String processingId, String shipmentId, String storeId) throws Exception{

		ConsumerOrder consumerOrder = new ConsumerOrder();
		
		
		consumerOrder.setTitle(title);		
		RetailStoreMember  consumer = new RetailStoreMember();
		consumer.setId(consumerId);		
		consumerOrder.setConsumer(consumer);		
		SupplyOrderConfirmation  confirmation = new SupplyOrderConfirmation();
		confirmation.setId(confirmationId);		
		consumerOrder.setConfirmation(confirmation);		
		SupplyOrderApproval  approval = new SupplyOrderApproval();
		approval.setId(approvalId);		
		consumerOrder.setApproval(approval);		
		SupplyOrderProcessing  processing = new SupplyOrderProcessing();
		processing.setId(processingId);		
		consumerOrder.setProcessing(processing);		
		SupplyOrderShipment  shipment = new SupplyOrderShipment();
		shipment.setId(shipmentId);		
		consumerOrder.setShipment(shipment);		
		RetailStore  store = new RetailStore();
		store.setId(storeId);		
		consumerOrder.setStore(store);		
		consumerOrder.setLastUpdateTime(userContext.now());
	
		
		return consumerOrder;
	
		
	}
	
	protected ConsumerOrder createIndexedConsumerOrder(String id, int version){

		ConsumerOrder consumerOrder = new ConsumerOrder();
		consumerOrder.setId(id);
		consumerOrder.setVersion(version);
		return consumerOrder;			
		
	}
	
	protected void checkParamsForRemovingConsumerOrderList(RetailscmUserContext userContext, String supplyOrderDeliveryId, 
			String consumerOrderIds[],String [] tokensExpr) throws Exception {
		
<<<<<<< HEAD
		userContext.getChecker().checkIdOfSupplyOrderDelivery(supplyOrderDeliveryId);
		for(String consumerOrderIdItem: consumerOrderIds){
			userContext.getChecker().checkIdOfConsumerOrder(consumerOrderIdItem);
=======
		checkerOf(userContext).checkIdOfSupplyOrderDelivery(supplyOrderDeliveryId);
		for(String consumerOrderIdItem: consumerOrderIds){
			checkerOf(userContext).checkIdOfConsumerOrder(consumerOrderIdItem);
>>>>>>> ea67698ef1c4e94c89147baaf9f93aa768973fbe
		}
		
		checkerOf(userContext).throwExceptionIfHasErrors(SupplyOrderDeliveryManagerException.class);
		
	}
	public  SupplyOrderDelivery removeConsumerOrderList(RetailscmUserContext userContext, String supplyOrderDeliveryId, 
			String consumerOrderIds[],String [] tokensExpr) throws Exception{
			
			checkParamsForRemovingConsumerOrderList(userContext, supplyOrderDeliveryId,  consumerOrderIds, tokensExpr);
			
			
			SupplyOrderDelivery supplyOrderDelivery = loadSupplyOrderDelivery(userContext, supplyOrderDeliveryId, allTokens());
			synchronized(supplyOrderDelivery){ 
				//Will be good when the supplyOrderDelivery loaded from this JVM process cache.
				//Also good when there is a RAM based DAO implementation
				supplyOrderDeliveryDaoOf(userContext).planToRemoveConsumerOrderList(supplyOrderDelivery, consumerOrderIds, allTokens());
				supplyOrderDelivery = saveSupplyOrderDelivery(userContext, supplyOrderDelivery, tokens().withConsumerOrderList().done());
				deleteRelationListInGraph(userContext, supplyOrderDelivery.getConsumerOrderList());
				return present(userContext,supplyOrderDelivery, mergedAllTokens(tokensExpr));
			}
	}
	
	protected void checkParamsForRemovingConsumerOrder(RetailscmUserContext userContext, String supplyOrderDeliveryId, 
		String consumerOrderId, int consumerOrderVersion,String [] tokensExpr) throws Exception{
		
		checkerOf(userContext).checkIdOfSupplyOrderDelivery( supplyOrderDeliveryId);
		checkerOf(userContext).checkIdOfConsumerOrder(consumerOrderId);
		checkerOf(userContext).checkVersionOfConsumerOrder(consumerOrderVersion);
		checkerOf(userContext).throwExceptionIfHasErrors(SupplyOrderDeliveryManagerException.class);
	
	}
	public  SupplyOrderDelivery removeConsumerOrder(RetailscmUserContext userContext, String supplyOrderDeliveryId, 
		String consumerOrderId, int consumerOrderVersion,String [] tokensExpr) throws Exception{
		
		checkParamsForRemovingConsumerOrder(userContext,supplyOrderDeliveryId, consumerOrderId, consumerOrderVersion,tokensExpr);
		
		ConsumerOrder consumerOrder = createIndexedConsumerOrder(consumerOrderId, consumerOrderVersion);
		SupplyOrderDelivery supplyOrderDelivery = loadSupplyOrderDelivery(userContext, supplyOrderDeliveryId, allTokens());
		synchronized(supplyOrderDelivery){ 
			//Will be good when the supplyOrderDelivery loaded from this JVM process cache.
			//Also good when there is a RAM based DAO implementation
			supplyOrderDelivery.removeConsumerOrder( consumerOrder );		
			supplyOrderDelivery = saveSupplyOrderDelivery(userContext, supplyOrderDelivery, tokens().withConsumerOrderList().done());
			deleteRelationInGraph(userContext, consumerOrder);
			return present(userContext,supplyOrderDelivery, mergedAllTokens(tokensExpr));
		}
		
		
	}
	protected void checkParamsForCopyingConsumerOrder(RetailscmUserContext userContext, String supplyOrderDeliveryId, 
		String consumerOrderId, int consumerOrderVersion,String [] tokensExpr) throws Exception{
		
		checkerOf(userContext).checkIdOfSupplyOrderDelivery( supplyOrderDeliveryId);
		checkerOf(userContext).checkIdOfConsumerOrder(consumerOrderId);
		checkerOf(userContext).checkVersionOfConsumerOrder(consumerOrderVersion);
		checkerOf(userContext).throwExceptionIfHasErrors(SupplyOrderDeliveryManagerException.class);
	
	}
	public  SupplyOrderDelivery copyConsumerOrderFrom(RetailscmUserContext userContext, String supplyOrderDeliveryId, 
		String consumerOrderId, int consumerOrderVersion,String [] tokensExpr) throws Exception{
		
		checkParamsForCopyingConsumerOrder(userContext,supplyOrderDeliveryId, consumerOrderId, consumerOrderVersion,tokensExpr);
		
		ConsumerOrder consumerOrder = createIndexedConsumerOrder(consumerOrderId, consumerOrderVersion);
		SupplyOrderDelivery supplyOrderDelivery = loadSupplyOrderDelivery(userContext, supplyOrderDeliveryId, allTokens());
		synchronized(supplyOrderDelivery){ 
			//Will be good when the supplyOrderDelivery loaded from this JVM process cache.
			//Also good when there is a RAM based DAO implementation
			
			consumerOrder.updateLastUpdateTime(userContext.now());
			
			supplyOrderDelivery.copyConsumerOrderFrom( consumerOrder );		
			supplyOrderDelivery = saveSupplyOrderDelivery(userContext, supplyOrderDelivery, tokens().withConsumerOrderList().done());
			
			userContext.getManagerGroup().getConsumerOrderManager().onNewInstanceCreated(userContext, (ConsumerOrder)supplyOrderDelivery.getFlexiableObjects().get(BaseEntity.COPIED_CHILD));
			return present(userContext,supplyOrderDelivery, mergedAllTokens(tokensExpr));
		}
		
	}
	
	protected void checkParamsForUpdatingConsumerOrder(RetailscmUserContext userContext, String supplyOrderDeliveryId, String consumerOrderId, int consumerOrderVersion, String property, String newValueExpr,String [] tokensExpr) throws Exception{
		

		
		checkerOf(userContext).checkIdOfSupplyOrderDelivery(supplyOrderDeliveryId);
		checkerOf(userContext).checkIdOfConsumerOrder(consumerOrderId);
		checkerOf(userContext).checkVersionOfConsumerOrder(consumerOrderVersion);
		

		if(ConsumerOrder.TITLE_PROPERTY.equals(property)){
			checkerOf(userContext).checkTitleOfConsumerOrder(parseString(newValueExpr));
		}
		
	
		checkerOf(userContext).throwExceptionIfHasErrors(SupplyOrderDeliveryManagerException.class);
	
	}
	
	public  SupplyOrderDelivery updateConsumerOrder(RetailscmUserContext userContext, String supplyOrderDeliveryId, String consumerOrderId, int consumerOrderVersion, String property, String newValueExpr,String [] tokensExpr)
			throws Exception{
		
		checkParamsForUpdatingConsumerOrder(userContext, supplyOrderDeliveryId, consumerOrderId, consumerOrderVersion, property, newValueExpr,  tokensExpr);
		
		Map<String,Object> loadTokens = this.tokens().withConsumerOrderList().searchConsumerOrderListWith(ConsumerOrder.ID_PROPERTY, "eq", consumerOrderId).done();
		
		
		
		SupplyOrderDelivery supplyOrderDelivery = loadSupplyOrderDelivery(userContext, supplyOrderDeliveryId, loadTokens);
		
		synchronized(supplyOrderDelivery){ 
			//Will be good when the supplyOrderDelivery loaded from this JVM process cache.
			//Also good when there is a RAM based DAO implementation
			//supplyOrderDelivery.removeConsumerOrder( consumerOrder );	
			//make changes to AcceleraterAccount.
			ConsumerOrder consumerOrderIndex = createIndexedConsumerOrder(consumerOrderId, consumerOrderVersion);
		
			ConsumerOrder consumerOrder = supplyOrderDelivery.findTheConsumerOrder(consumerOrderIndex);
			if(consumerOrder == null){
				throw new SupplyOrderDeliveryManagerException(consumerOrder+" is NOT FOUND" );
			}
			
			consumerOrder.changeProperty(property, newValueExpr);
			consumerOrder.updateLastUpdateTime(userContext.now());
			supplyOrderDelivery = saveSupplyOrderDelivery(userContext, supplyOrderDelivery, tokens().withConsumerOrderList().done());
			return present(userContext,supplyOrderDelivery, mergedAllTokens(tokensExpr));
		}

	}
	/*

	*/
	



	protected void checkParamsForAddingSupplyOrder(RetailscmUserContext userContext, String supplyOrderDeliveryId, String buyerId, String sellerId, String title, BigDecimal totalAmount, String confirmationId, String approvalId, String processingId, String pickingId, String shipmentId,String [] tokensExpr) throws Exception{
		
				checkerOf(userContext).checkIdOfSupplyOrderDelivery(supplyOrderDeliveryId);

		
		checkerOf(userContext).checkBuyerIdOfSupplyOrder(buyerId);
		
		checkerOf(userContext).checkSellerIdOfSupplyOrder(sellerId);
		
		checkerOf(userContext).checkTitleOfSupplyOrder(title);
		
		checkerOf(userContext).checkTotalAmountOfSupplyOrder(totalAmount);
		
		checkerOf(userContext).checkConfirmationIdOfSupplyOrder(confirmationId);
		
		checkerOf(userContext).checkApprovalIdOfSupplyOrder(approvalId);
		
		checkerOf(userContext).checkProcessingIdOfSupplyOrder(processingId);
		
		checkerOf(userContext).checkPickingIdOfSupplyOrder(pickingId);
		
		checkerOf(userContext).checkShipmentIdOfSupplyOrder(shipmentId);
	
		checkerOf(userContext).throwExceptionIfHasErrors(SupplyOrderDeliveryManagerException.class);

	
	}
	public  SupplyOrderDelivery addSupplyOrder(RetailscmUserContext userContext, String supplyOrderDeliveryId, String buyerId, String sellerId, String title, BigDecimal totalAmount, String confirmationId, String approvalId, String processingId, String pickingId, String shipmentId, String [] tokensExpr) throws Exception
	{	
		
		checkParamsForAddingSupplyOrder(userContext,supplyOrderDeliveryId,buyerId, sellerId, title, totalAmount, confirmationId, approvalId, processingId, pickingId, shipmentId,tokensExpr);
		
		SupplyOrder supplyOrder = createSupplyOrder(userContext,buyerId, sellerId, title, totalAmount, confirmationId, approvalId, processingId, pickingId, shipmentId);
		
		SupplyOrderDelivery supplyOrderDelivery = loadSupplyOrderDelivery(userContext, supplyOrderDeliveryId, allTokens());
		synchronized(supplyOrderDelivery){ 
			//Will be good when the supplyOrderDelivery loaded from this JVM process cache.
			//Also good when there is a RAM based DAO implementation
			supplyOrderDelivery.addSupplyOrder( supplyOrder );		
			supplyOrderDelivery = saveSupplyOrderDelivery(userContext, supplyOrderDelivery, tokens().withSupplyOrderList().done());
			
			userContext.getManagerGroup().getSupplyOrderManager().onNewInstanceCreated(userContext, supplyOrder);
			return present(userContext,supplyOrderDelivery, mergedAllTokens(tokensExpr));
		}
	}
	protected void checkParamsForUpdatingSupplyOrderProperties(RetailscmUserContext userContext, String supplyOrderDeliveryId,String id,String title,BigDecimal totalAmount,String [] tokensExpr) throws Exception {
		
		checkerOf(userContext).checkIdOfSupplyOrderDelivery(supplyOrderDeliveryId);
		checkerOf(userContext).checkIdOfSupplyOrder(id);
		
		checkerOf(userContext).checkTitleOfSupplyOrder( title);
		checkerOf(userContext).checkTotalAmountOfSupplyOrder( totalAmount);

		checkerOf(userContext).throwExceptionIfHasErrors(SupplyOrderDeliveryManagerException.class);
		
	}
	public  SupplyOrderDelivery updateSupplyOrderProperties(RetailscmUserContext userContext, String supplyOrderDeliveryId, String id,String title,BigDecimal totalAmount, String [] tokensExpr) throws Exception
	{	
		checkParamsForUpdatingSupplyOrderProperties(userContext,supplyOrderDeliveryId,id,title,totalAmount,tokensExpr);

		Map<String, Object> options = tokens()
				.allTokens()
				//.withSupplyOrderListList()
				.searchSupplyOrderListWith(SupplyOrder.ID_PROPERTY, "is", id).done();
		
		SupplyOrderDelivery supplyOrderDeliveryToUpdate = loadSupplyOrderDelivery(userContext, supplyOrderDeliveryId, options);
		
		if(supplyOrderDeliveryToUpdate.getSupplyOrderList().isEmpty()){
			throw new SupplyOrderDeliveryManagerException("SupplyOrder is NOT FOUND with id: '"+id+"'");
		}
		
		SupplyOrder item = supplyOrderDeliveryToUpdate.getSupplyOrderList().first();
		
		item.updateTitle( title );
		item.updateTotalAmount( totalAmount );

		
		//checkParamsForAddingSupplyOrder(userContext,supplyOrderDeliveryId,name, code, used,tokensExpr);
		SupplyOrderDelivery supplyOrderDelivery = saveSupplyOrderDelivery(userContext, supplyOrderDeliveryToUpdate, tokens().withSupplyOrderList().done());
		synchronized(supplyOrderDelivery){ 
			return present(userContext,supplyOrderDelivery, mergedAllTokens(tokensExpr));
		}
	}
	
	
	protected SupplyOrder createSupplyOrder(RetailscmUserContext userContext, String buyerId, String sellerId, String title, BigDecimal totalAmount, String confirmationId, String approvalId, String processingId, String pickingId, String shipmentId) throws Exception{

		SupplyOrder supplyOrder = new SupplyOrder();
		
		
		RetailStoreCountryCenter  buyer = new RetailStoreCountryCenter();
		buyer.setId(buyerId);		
		supplyOrder.setBuyer(buyer);		
		GoodsSupplier  seller = new GoodsSupplier();
		seller.setId(sellerId);		
		supplyOrder.setSeller(seller);		
		supplyOrder.setTitle(title);		
		supplyOrder.setTotalAmount(totalAmount);		
		SupplyOrderConfirmation  confirmation = new SupplyOrderConfirmation();
		confirmation.setId(confirmationId);		
		supplyOrder.setConfirmation(confirmation);		
		SupplyOrderApproval  approval = new SupplyOrderApproval();
		approval.setId(approvalId);		
		supplyOrder.setApproval(approval);		
		SupplyOrderProcessing  processing = new SupplyOrderProcessing();
		processing.setId(processingId);		
		supplyOrder.setProcessing(processing);		
		SupplyOrderPicking  picking = new SupplyOrderPicking();
		picking.setId(pickingId);		
		supplyOrder.setPicking(picking);		
		SupplyOrderShipment  shipment = new SupplyOrderShipment();
		shipment.setId(shipmentId);		
		supplyOrder.setShipment(shipment);		
		supplyOrder.setLastUpdateTime(userContext.now());
	
		
		return supplyOrder;
	
		
	}
	
	protected SupplyOrder createIndexedSupplyOrder(String id, int version){

		SupplyOrder supplyOrder = new SupplyOrder();
		supplyOrder.setId(id);
		supplyOrder.setVersion(version);
		return supplyOrder;			
		
	}
	
	protected void checkParamsForRemovingSupplyOrderList(RetailscmUserContext userContext, String supplyOrderDeliveryId, 
			String supplyOrderIds[],String [] tokensExpr) throws Exception {
		
<<<<<<< HEAD
		userContext.getChecker().checkIdOfSupplyOrderDelivery(supplyOrderDeliveryId);
		for(String supplyOrderIdItem: supplyOrderIds){
			userContext.getChecker().checkIdOfSupplyOrder(supplyOrderIdItem);
=======
		checkerOf(userContext).checkIdOfSupplyOrderDelivery(supplyOrderDeliveryId);
		for(String supplyOrderIdItem: supplyOrderIds){
			checkerOf(userContext).checkIdOfSupplyOrder(supplyOrderIdItem);
>>>>>>> ea67698ef1c4e94c89147baaf9f93aa768973fbe
		}
		
		checkerOf(userContext).throwExceptionIfHasErrors(SupplyOrderDeliveryManagerException.class);
		
	}
	public  SupplyOrderDelivery removeSupplyOrderList(RetailscmUserContext userContext, String supplyOrderDeliveryId, 
			String supplyOrderIds[],String [] tokensExpr) throws Exception{
			
			checkParamsForRemovingSupplyOrderList(userContext, supplyOrderDeliveryId,  supplyOrderIds, tokensExpr);
			
			
			SupplyOrderDelivery supplyOrderDelivery = loadSupplyOrderDelivery(userContext, supplyOrderDeliveryId, allTokens());
			synchronized(supplyOrderDelivery){ 
				//Will be good when the supplyOrderDelivery loaded from this JVM process cache.
				//Also good when there is a RAM based DAO implementation
				supplyOrderDeliveryDaoOf(userContext).planToRemoveSupplyOrderList(supplyOrderDelivery, supplyOrderIds, allTokens());
				supplyOrderDelivery = saveSupplyOrderDelivery(userContext, supplyOrderDelivery, tokens().withSupplyOrderList().done());
				deleteRelationListInGraph(userContext, supplyOrderDelivery.getSupplyOrderList());
				return present(userContext,supplyOrderDelivery, mergedAllTokens(tokensExpr));
			}
	}
	
	protected void checkParamsForRemovingSupplyOrder(RetailscmUserContext userContext, String supplyOrderDeliveryId, 
		String supplyOrderId, int supplyOrderVersion,String [] tokensExpr) throws Exception{
		
		checkerOf(userContext).checkIdOfSupplyOrderDelivery( supplyOrderDeliveryId);
		checkerOf(userContext).checkIdOfSupplyOrder(supplyOrderId);
		checkerOf(userContext).checkVersionOfSupplyOrder(supplyOrderVersion);
		checkerOf(userContext).throwExceptionIfHasErrors(SupplyOrderDeliveryManagerException.class);
	
	}
	public  SupplyOrderDelivery removeSupplyOrder(RetailscmUserContext userContext, String supplyOrderDeliveryId, 
		String supplyOrderId, int supplyOrderVersion,String [] tokensExpr) throws Exception{
		
		checkParamsForRemovingSupplyOrder(userContext,supplyOrderDeliveryId, supplyOrderId, supplyOrderVersion,tokensExpr);
		
		SupplyOrder supplyOrder = createIndexedSupplyOrder(supplyOrderId, supplyOrderVersion);
		SupplyOrderDelivery supplyOrderDelivery = loadSupplyOrderDelivery(userContext, supplyOrderDeliveryId, allTokens());
		synchronized(supplyOrderDelivery){ 
			//Will be good when the supplyOrderDelivery loaded from this JVM process cache.
			//Also good when there is a RAM based DAO implementation
			supplyOrderDelivery.removeSupplyOrder( supplyOrder );		
			supplyOrderDelivery = saveSupplyOrderDelivery(userContext, supplyOrderDelivery, tokens().withSupplyOrderList().done());
			deleteRelationInGraph(userContext, supplyOrder);
			return present(userContext,supplyOrderDelivery, mergedAllTokens(tokensExpr));
		}
		
		
	}
	protected void checkParamsForCopyingSupplyOrder(RetailscmUserContext userContext, String supplyOrderDeliveryId, 
		String supplyOrderId, int supplyOrderVersion,String [] tokensExpr) throws Exception{
		
		checkerOf(userContext).checkIdOfSupplyOrderDelivery( supplyOrderDeliveryId);
		checkerOf(userContext).checkIdOfSupplyOrder(supplyOrderId);
		checkerOf(userContext).checkVersionOfSupplyOrder(supplyOrderVersion);
		checkerOf(userContext).throwExceptionIfHasErrors(SupplyOrderDeliveryManagerException.class);
	
	}
	public  SupplyOrderDelivery copySupplyOrderFrom(RetailscmUserContext userContext, String supplyOrderDeliveryId, 
		String supplyOrderId, int supplyOrderVersion,String [] tokensExpr) throws Exception{
		
		checkParamsForCopyingSupplyOrder(userContext,supplyOrderDeliveryId, supplyOrderId, supplyOrderVersion,tokensExpr);
		
		SupplyOrder supplyOrder = createIndexedSupplyOrder(supplyOrderId, supplyOrderVersion);
		SupplyOrderDelivery supplyOrderDelivery = loadSupplyOrderDelivery(userContext, supplyOrderDeliveryId, allTokens());
		synchronized(supplyOrderDelivery){ 
			//Will be good when the supplyOrderDelivery loaded from this JVM process cache.
			//Also good when there is a RAM based DAO implementation
			
			supplyOrder.updateLastUpdateTime(userContext.now());
			
			supplyOrderDelivery.copySupplyOrderFrom( supplyOrder );		
			supplyOrderDelivery = saveSupplyOrderDelivery(userContext, supplyOrderDelivery, tokens().withSupplyOrderList().done());
			
			userContext.getManagerGroup().getSupplyOrderManager().onNewInstanceCreated(userContext, (SupplyOrder)supplyOrderDelivery.getFlexiableObjects().get(BaseEntity.COPIED_CHILD));
			return present(userContext,supplyOrderDelivery, mergedAllTokens(tokensExpr));
		}
		
	}
	
	protected void checkParamsForUpdatingSupplyOrder(RetailscmUserContext userContext, String supplyOrderDeliveryId, String supplyOrderId, int supplyOrderVersion, String property, String newValueExpr,String [] tokensExpr) throws Exception{
		

		
		checkerOf(userContext).checkIdOfSupplyOrderDelivery(supplyOrderDeliveryId);
		checkerOf(userContext).checkIdOfSupplyOrder(supplyOrderId);
		checkerOf(userContext).checkVersionOfSupplyOrder(supplyOrderVersion);
		

		if(SupplyOrder.TITLE_PROPERTY.equals(property)){
			checkerOf(userContext).checkTitleOfSupplyOrder(parseString(newValueExpr));
		}
		
		if(SupplyOrder.TOTAL_AMOUNT_PROPERTY.equals(property)){
			checkerOf(userContext).checkTotalAmountOfSupplyOrder(parseBigDecimal(newValueExpr));
		}
		
	
		checkerOf(userContext).throwExceptionIfHasErrors(SupplyOrderDeliveryManagerException.class);
	
	}
	
	public  SupplyOrderDelivery updateSupplyOrder(RetailscmUserContext userContext, String supplyOrderDeliveryId, String supplyOrderId, int supplyOrderVersion, String property, String newValueExpr,String [] tokensExpr)
			throws Exception{
		
		checkParamsForUpdatingSupplyOrder(userContext, supplyOrderDeliveryId, supplyOrderId, supplyOrderVersion, property, newValueExpr,  tokensExpr);
		
		Map<String,Object> loadTokens = this.tokens().withSupplyOrderList().searchSupplyOrderListWith(SupplyOrder.ID_PROPERTY, "eq", supplyOrderId).done();
		
		
		
		SupplyOrderDelivery supplyOrderDelivery = loadSupplyOrderDelivery(userContext, supplyOrderDeliveryId, loadTokens);
		
		synchronized(supplyOrderDelivery){ 
			//Will be good when the supplyOrderDelivery loaded from this JVM process cache.
			//Also good when there is a RAM based DAO implementation
			//supplyOrderDelivery.removeSupplyOrder( supplyOrder );	
			//make changes to AcceleraterAccount.
			SupplyOrder supplyOrderIndex = createIndexedSupplyOrder(supplyOrderId, supplyOrderVersion);
		
			SupplyOrder supplyOrder = supplyOrderDelivery.findTheSupplyOrder(supplyOrderIndex);
			if(supplyOrder == null){
				throw new SupplyOrderDeliveryManagerException(supplyOrder+" is NOT FOUND" );
			}
			
			supplyOrder.changeProperty(property, newValueExpr);
			supplyOrder.updateLastUpdateTime(userContext.now());
			supplyOrderDelivery = saveSupplyOrderDelivery(userContext, supplyOrderDelivery, tokens().withSupplyOrderList().done());
			return present(userContext,supplyOrderDelivery, mergedAllTokens(tokensExpr));
		}

	}
	/*

	*/
	



	public void onNewInstanceCreated(RetailscmUserContext userContext, SupplyOrderDelivery newCreated) throws Exception{
		ensureRelationInGraph(userContext, newCreated);
		sendCreationEvent(userContext, newCreated);
	}

}


