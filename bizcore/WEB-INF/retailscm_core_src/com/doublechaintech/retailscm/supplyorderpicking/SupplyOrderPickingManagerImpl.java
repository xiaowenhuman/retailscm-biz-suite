
package com.doublechaintech.retailscm.supplyorderpicking;

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

import com.doublechaintech.retailscm.supplyorder.SupplyOrder;


import com.doublechaintech.retailscm.supplyorderapproval.SupplyOrderApproval;
import com.doublechaintech.retailscm.retailstorecountrycenter.RetailStoreCountryCenter;
import com.doublechaintech.retailscm.goodssupplier.GoodsSupplier;
import com.doublechaintech.retailscm.supplyorderdelivery.SupplyOrderDelivery;
import com.doublechaintech.retailscm.supplyorderpicking.SupplyOrderPicking;
import com.doublechaintech.retailscm.supplyorderprocessing.SupplyOrderProcessing;
import com.doublechaintech.retailscm.supplyorderconfirmation.SupplyOrderConfirmation;
import com.doublechaintech.retailscm.supplyordershipment.SupplyOrderShipment;






public class SupplyOrderPickingManagerImpl extends CustomRetailscmCheckerManager implements SupplyOrderPickingManager {
	
	private static final String SERVICE_TYPE = "SupplyOrderPicking";
	@Override
	public SupplyOrderPickingDAO daoOf(RetailscmUserContext userContext) {
		return supplyOrderPickingDaoOf(userContext);
	}
	
	@Override
	public String serviceFor(){
		return SERVICE_TYPE;
	}
	
	
	protected void throwExceptionWithMessage(String value) throws SupplyOrderPickingManagerException{
	
		Message message = new Message();
		message.setBody(value);
		throw new SupplyOrderPickingManagerException(message);

	}
	
	

 	protected SupplyOrderPicking saveSupplyOrderPicking(RetailscmUserContext userContext, SupplyOrderPicking supplyOrderPicking, String [] tokensExpr) throws Exception{	
 		//return getSupplyOrderPickingDAO().save(supplyOrderPicking, tokens);
 		
 		Map<String,Object>tokens = parseTokens(tokensExpr);
 		
 		return saveSupplyOrderPicking(userContext, supplyOrderPicking, tokens);
 	}
 	
 	protected SupplyOrderPicking saveSupplyOrderPickingDetail(RetailscmUserContext userContext, SupplyOrderPicking supplyOrderPicking) throws Exception{	

 		
 		return saveSupplyOrderPicking(userContext, supplyOrderPicking, allTokens());
 	}
 	
 	public SupplyOrderPicking loadSupplyOrderPicking(RetailscmUserContext userContext, String supplyOrderPickingId, String [] tokensExpr) throws Exception{				
 
 		checkerOf(userContext).checkIdOfSupplyOrderPicking(supplyOrderPickingId);
		checkerOf(userContext).throwExceptionIfHasErrors( SupplyOrderPickingManagerException.class);

 			
 		Map<String,Object>tokens = parseTokens(tokensExpr);
 		
 		SupplyOrderPicking supplyOrderPicking = loadSupplyOrderPicking( userContext, supplyOrderPickingId, tokens);
 		//do some calc before sent to customer?
 		return present(userContext,supplyOrderPicking, tokens);
 	}
 	
 	
 	 public SupplyOrderPicking searchSupplyOrderPicking(RetailscmUserContext userContext, String supplyOrderPickingId, String textToSearch,String [] tokensExpr) throws Exception{				
 
 		checkerOf(userContext).checkIdOfSupplyOrderPicking(supplyOrderPickingId);
		checkerOf(userContext).throwExceptionIfHasErrors( SupplyOrderPickingManagerException.class);

 		
 		Map<String,Object>tokens = tokens().allTokens().searchEntireObjectText("startsWith", textToSearch).initWithArray(tokensExpr);
 		
 		SupplyOrderPicking supplyOrderPicking = loadSupplyOrderPicking( userContext, supplyOrderPickingId, tokens);
 		//do some calc before sent to customer?
 		return present(userContext,supplyOrderPicking, tokens);
 	}
 	
 	

 	protected SupplyOrderPicking present(RetailscmUserContext userContext, SupplyOrderPicking supplyOrderPicking, Map<String, Object> tokens) throws Exception {
		
		
		addActions(userContext,supplyOrderPicking,tokens);
		
		
		SupplyOrderPicking  supplyOrderPickingToPresent = supplyOrderPickingDaoOf(userContext).present(supplyOrderPicking, tokens);
		
		List<BaseEntity> entityListToNaming = supplyOrderPickingToPresent.collectRefercencesFromLists();
		supplyOrderPickingDaoOf(userContext).alias(entityListToNaming);
		
		return  supplyOrderPickingToPresent;
		
		
	}
 
 	
 	
 	public SupplyOrderPicking loadSupplyOrderPickingDetail(RetailscmUserContext userContext, String supplyOrderPickingId) throws Exception{	
 		SupplyOrderPicking supplyOrderPicking = loadSupplyOrderPicking( userContext, supplyOrderPickingId, allTokens());
 		return present(userContext,supplyOrderPicking, allTokens());
		
 	}
 	
 	public Object view(RetailscmUserContext userContext, String supplyOrderPickingId) throws Exception{	
 		SupplyOrderPicking supplyOrderPicking = loadSupplyOrderPicking( userContext, supplyOrderPickingId, viewTokens());
 		return present(userContext,supplyOrderPicking, allTokens());
		
 	}
 	protected SupplyOrderPicking saveSupplyOrderPicking(RetailscmUserContext userContext, SupplyOrderPicking supplyOrderPicking, Map<String,Object>tokens) throws Exception{	
 		return supplyOrderPickingDaoOf(userContext).save(supplyOrderPicking, tokens);
 	}
 	protected SupplyOrderPicking loadSupplyOrderPicking(RetailscmUserContext userContext, String supplyOrderPickingId, Map<String,Object>tokens) throws Exception{	
		checkerOf(userContext).checkIdOfSupplyOrderPicking(supplyOrderPickingId);
		checkerOf(userContext).throwExceptionIfHasErrors( SupplyOrderPickingManagerException.class);

 
 		return supplyOrderPickingDaoOf(userContext).load(supplyOrderPickingId, tokens);
 	}

	


 	


 	
 	
 	protected<T extends BaseEntity> void addActions(RetailscmUserContext userContext, SupplyOrderPicking supplyOrderPicking, Map<String, Object> tokens){
		super.addActions(userContext, supplyOrderPicking, tokens);
		
		addAction(userContext, supplyOrderPicking, tokens,"@create","createSupplyOrderPicking","createSupplyOrderPicking/","main","primary");
		addAction(userContext, supplyOrderPicking, tokens,"@update","updateSupplyOrderPicking","updateSupplyOrderPicking/"+supplyOrderPicking.getId()+"/","main","primary");
		addAction(userContext, supplyOrderPicking, tokens,"@copy","cloneSupplyOrderPicking","cloneSupplyOrderPicking/"+supplyOrderPicking.getId()+"/","main","primary");
		
		addAction(userContext, supplyOrderPicking, tokens,"supply_order_picking.addSupplyOrder","addSupplyOrder","addSupplyOrder/"+supplyOrderPicking.getId()+"/","supplyOrderList","primary");
		addAction(userContext, supplyOrderPicking, tokens,"supply_order_picking.removeSupplyOrder","removeSupplyOrder","removeSupplyOrder/"+supplyOrderPicking.getId()+"/","supplyOrderList","primary");
		addAction(userContext, supplyOrderPicking, tokens,"supply_order_picking.updateSupplyOrder","updateSupplyOrder","updateSupplyOrder/"+supplyOrderPicking.getId()+"/","supplyOrderList","primary");
		addAction(userContext, supplyOrderPicking, tokens,"supply_order_picking.copySupplyOrderFrom","copySupplyOrderFrom","copySupplyOrderFrom/"+supplyOrderPicking.getId()+"/","supplyOrderList","primary");
	
		
		
	}// end method of protected<T extends BaseEntity> void addActions(RetailscmUserContext userContext, SupplyOrderPicking supplyOrderPicking, Map<String, Object> tokens){
	
 	
 	
 
 	
 	

	public SupplyOrderPicking createSupplyOrderPicking(RetailscmUserContext userContext, String who,Date processTime) throws Exception
	//public SupplyOrderPicking createSupplyOrderPicking(RetailscmUserContext userContext,String who, Date processTime) throws Exception
	{
		
		

		

		checkerOf(userContext).checkWhoOfSupplyOrderPicking(who);
		checkerOf(userContext).checkProcessTimeOfSupplyOrderPicking(processTime);
	
		checkerOf(userContext).throwExceptionIfHasErrors(SupplyOrderPickingManagerException.class);


		SupplyOrderPicking supplyOrderPicking=createNewSupplyOrderPicking();	

		supplyOrderPicking.setWho(who);
		supplyOrderPicking.setProcessTime(processTime);

		supplyOrderPicking = saveSupplyOrderPicking(userContext, supplyOrderPicking, emptyOptions());
		
		onNewInstanceCreated(userContext, supplyOrderPicking);
		return supplyOrderPicking;

		
	}
	protected SupplyOrderPicking createNewSupplyOrderPicking() 
	{
		
		return new SupplyOrderPicking();		
	}
	
	protected void checkParamsForUpdatingSupplyOrderPicking(RetailscmUserContext userContext,String supplyOrderPickingId, int supplyOrderPickingVersion, String property, String newValueExpr,String [] tokensExpr)throws Exception
	{
		

		
		
		checkerOf(userContext).checkIdOfSupplyOrderPicking(supplyOrderPickingId);
		checkerOf(userContext).checkVersionOfSupplyOrderPicking( supplyOrderPickingVersion);
		

		if(SupplyOrderPicking.WHO_PROPERTY.equals(property)){
			checkerOf(userContext).checkWhoOfSupplyOrderPicking(parseString(newValueExpr));
		}
		if(SupplyOrderPicking.PROCESS_TIME_PROPERTY.equals(property)){
			checkerOf(userContext).checkProcessTimeOfSupplyOrderPicking(parseDate(newValueExpr));
		}
	
		checkerOf(userContext).throwExceptionIfHasErrors(SupplyOrderPickingManagerException.class);
	
		
	}
	
	
	
	public SupplyOrderPicking clone(RetailscmUserContext userContext, String fromSupplyOrderPickingId) throws Exception{
		
		return supplyOrderPickingDaoOf(userContext).clone(fromSupplyOrderPickingId, this.allTokens());
	}
	
	public SupplyOrderPicking internalSaveSupplyOrderPicking(RetailscmUserContext userContext, SupplyOrderPicking supplyOrderPicking) throws Exception 
	{
		return internalSaveSupplyOrderPicking(userContext, supplyOrderPicking, allTokens());

	}
	public SupplyOrderPicking internalSaveSupplyOrderPicking(RetailscmUserContext userContext, SupplyOrderPicking supplyOrderPicking, Map<String,Object> options) throws Exception 
	{
		//checkParamsForUpdatingSupplyOrderPicking(userContext, supplyOrderPickingId, supplyOrderPickingVersion, property, newValueExpr, tokensExpr);
		
		
		synchronized(supplyOrderPicking){ 
			//will be good when the supplyOrderPicking loaded from this JVM process cache.
			//also good when there is a ram based DAO implementation
			//make changes to SupplyOrderPicking.
			if (supplyOrderPicking.isChanged()){
			
			}
			supplyOrderPicking = saveSupplyOrderPicking(userContext, supplyOrderPicking, options);
			return supplyOrderPicking;
			
		}

	}
	
	public SupplyOrderPicking updateSupplyOrderPicking(RetailscmUserContext userContext,String supplyOrderPickingId, int supplyOrderPickingVersion, String property, String newValueExpr,String [] tokensExpr) throws Exception 
	{
		checkParamsForUpdatingSupplyOrderPicking(userContext, supplyOrderPickingId, supplyOrderPickingVersion, property, newValueExpr, tokensExpr);
		
		
		
		SupplyOrderPicking supplyOrderPicking = loadSupplyOrderPicking(userContext, supplyOrderPickingId, allTokens());
		if(supplyOrderPicking.getVersion() != supplyOrderPickingVersion){
			String message = "The target version("+supplyOrderPicking.getVersion()+") is not equals to version("+supplyOrderPickingVersion+") provided";
			throwExceptionWithMessage(message);
		}
		synchronized(supplyOrderPicking){ 
			//will be good when the supplyOrderPicking loaded from this JVM process cache.
			//also good when there is a ram based DAO implementation
			//make changes to SupplyOrderPicking.
			
			supplyOrderPicking.changeProperty(property, newValueExpr);
			supplyOrderPicking = saveSupplyOrderPicking(userContext, supplyOrderPicking, tokens().done());
			return present(userContext,supplyOrderPicking, mergedAllTokens(tokensExpr));
			//return saveSupplyOrderPicking(userContext, supplyOrderPicking, tokens().done());
		}

	}
	
	public SupplyOrderPicking updateSupplyOrderPickingProperty(RetailscmUserContext userContext,String supplyOrderPickingId, int supplyOrderPickingVersion, String property, String newValueExpr,String [] tokensExpr) throws Exception 
	{
		checkParamsForUpdatingSupplyOrderPicking(userContext, supplyOrderPickingId, supplyOrderPickingVersion, property, newValueExpr, tokensExpr);
		
		SupplyOrderPicking supplyOrderPicking = loadSupplyOrderPicking(userContext, supplyOrderPickingId, allTokens());
		if(supplyOrderPicking.getVersion() != supplyOrderPickingVersion){
			String message = "The target version("+supplyOrderPicking.getVersion()+") is not equals to version("+supplyOrderPickingVersion+") provided";
			throwExceptionWithMessage(message);
		}
		synchronized(supplyOrderPicking){ 
			//will be good when the supplyOrderPicking loaded from this JVM process cache.
			//also good when there is a ram based DAO implementation
			//make changes to SupplyOrderPicking.
			
			supplyOrderPicking.changeProperty(property, newValueExpr);
			
			supplyOrderPicking = saveSupplyOrderPicking(userContext, supplyOrderPicking, tokens().done());
			return present(userContext,supplyOrderPicking, mergedAllTokens(tokensExpr));
			//return saveSupplyOrderPicking(userContext, supplyOrderPicking, tokens().done());
		}

	}
	protected Map<String,Object> emptyOptions(){
		return tokens().done();
	}
	
	protected SupplyOrderPickingTokens tokens(){
		return SupplyOrderPickingTokens.start();
	}
	protected Map<String,Object> parseTokens(String [] tokensExpr){
		return tokens().initWithArray(tokensExpr);
	}
	protected Map<String,Object> allTokens(){
		return SupplyOrderPickingTokens.all();
	}
	protected Map<String,Object> viewTokens(){
		return tokens().allTokens()
		.sortSupplyOrderListWith("id","desc")
		.analyzeAllLists().done();

	}
	protected Map<String,Object> mergedAllTokens(String []tokens){
		return SupplyOrderPickingTokens.mergeAll(tokens).done();
	}
	
//--------------------------------------------------------------
	
	//--------------------------------------------------------------

	public void delete(RetailscmUserContext userContext, String supplyOrderPickingId, int supplyOrderPickingVersion) throws Exception {
		//deleteInternal(userContext, supplyOrderPickingId, supplyOrderPickingVersion);		
	}
	protected void deleteInternal(RetailscmUserContext userContext,
			String supplyOrderPickingId, int supplyOrderPickingVersion) throws Exception{
			
		supplyOrderPickingDaoOf(userContext).delete(supplyOrderPickingId, supplyOrderPickingVersion);
	}
	
	public SupplyOrderPicking forgetByAll(RetailscmUserContext userContext, String supplyOrderPickingId, int supplyOrderPickingVersion) throws Exception {
		return forgetByAllInternal(userContext, supplyOrderPickingId, supplyOrderPickingVersion);		
	}
	protected SupplyOrderPicking forgetByAllInternal(RetailscmUserContext userContext,
			String supplyOrderPickingId, int supplyOrderPickingVersion) throws Exception{
			
		return supplyOrderPickingDaoOf(userContext).disconnectFromAll(supplyOrderPickingId, supplyOrderPickingVersion);
	}
	
	

	
	public int deleteAll(RetailscmUserContext userContext, String secureCode) throws Exception
	{
		/*
		if(!("dElEtEaLl".equals(secureCode))){
			throw new SupplyOrderPickingManagerException("Your secure code is not right, please guess again");
		}
		return deleteAllInternal(userContext);
		*/
		return 0;
	}
	
	
	protected int deleteAllInternal(RetailscmUserContext userContext) throws Exception{
		return supplyOrderPickingDaoOf(userContext).deleteAll();
	}


	//disconnect SupplyOrderPicking with buyer in SupplyOrder
	protected SupplyOrderPicking breakWithSupplyOrderByBuyer(RetailscmUserContext userContext, String supplyOrderPickingId, String buyerId,  String [] tokensExpr)
		 throws Exception{
			
			//TODO add check code here
			
			SupplyOrderPicking supplyOrderPicking = loadSupplyOrderPicking(userContext, supplyOrderPickingId, allTokens());

			synchronized(supplyOrderPicking){ 
				//Will be good when the thread loaded from this JVM process cache.
				//Also good when there is a RAM based DAO implementation
				
				supplyOrderPickingDaoOf(userContext).planToRemoveSupplyOrderListWithBuyer(supplyOrderPicking, buyerId, this.emptyOptions());

				supplyOrderPicking = saveSupplyOrderPicking(userContext, supplyOrderPicking, tokens().withSupplyOrderList().done());
				return supplyOrderPicking;
			}
	}
	//disconnect SupplyOrderPicking with seller in SupplyOrder
	protected SupplyOrderPicking breakWithSupplyOrderBySeller(RetailscmUserContext userContext, String supplyOrderPickingId, String sellerId,  String [] tokensExpr)
		 throws Exception{
			
			//TODO add check code here
			
			SupplyOrderPicking supplyOrderPicking = loadSupplyOrderPicking(userContext, supplyOrderPickingId, allTokens());

			synchronized(supplyOrderPicking){ 
				//Will be good when the thread loaded from this JVM process cache.
				//Also good when there is a RAM based DAO implementation
				
				supplyOrderPickingDaoOf(userContext).planToRemoveSupplyOrderListWithSeller(supplyOrderPicking, sellerId, this.emptyOptions());

				supplyOrderPicking = saveSupplyOrderPicking(userContext, supplyOrderPicking, tokens().withSupplyOrderList().done());
				return supplyOrderPicking;
			}
	}
	//disconnect SupplyOrderPicking with confirmation in SupplyOrder
	protected SupplyOrderPicking breakWithSupplyOrderByConfirmation(RetailscmUserContext userContext, String supplyOrderPickingId, String confirmationId,  String [] tokensExpr)
		 throws Exception{
			
			//TODO add check code here
			
			SupplyOrderPicking supplyOrderPicking = loadSupplyOrderPicking(userContext, supplyOrderPickingId, allTokens());

			synchronized(supplyOrderPicking){ 
				//Will be good when the thread loaded from this JVM process cache.
				//Also good when there is a RAM based DAO implementation
				
				supplyOrderPickingDaoOf(userContext).planToRemoveSupplyOrderListWithConfirmation(supplyOrderPicking, confirmationId, this.emptyOptions());

				supplyOrderPicking = saveSupplyOrderPicking(userContext, supplyOrderPicking, tokens().withSupplyOrderList().done());
				return supplyOrderPicking;
			}
	}
	//disconnect SupplyOrderPicking with approval in SupplyOrder
	protected SupplyOrderPicking breakWithSupplyOrderByApproval(RetailscmUserContext userContext, String supplyOrderPickingId, String approvalId,  String [] tokensExpr)
		 throws Exception{
			
			//TODO add check code here
			
			SupplyOrderPicking supplyOrderPicking = loadSupplyOrderPicking(userContext, supplyOrderPickingId, allTokens());

			synchronized(supplyOrderPicking){ 
				//Will be good when the thread loaded from this JVM process cache.
				//Also good when there is a RAM based DAO implementation
				
				supplyOrderPickingDaoOf(userContext).planToRemoveSupplyOrderListWithApproval(supplyOrderPicking, approvalId, this.emptyOptions());

				supplyOrderPicking = saveSupplyOrderPicking(userContext, supplyOrderPicking, tokens().withSupplyOrderList().done());
				return supplyOrderPicking;
			}
	}
	//disconnect SupplyOrderPicking with processing in SupplyOrder
	protected SupplyOrderPicking breakWithSupplyOrderByProcessing(RetailscmUserContext userContext, String supplyOrderPickingId, String processingId,  String [] tokensExpr)
		 throws Exception{
			
			//TODO add check code here
			
			SupplyOrderPicking supplyOrderPicking = loadSupplyOrderPicking(userContext, supplyOrderPickingId, allTokens());

			synchronized(supplyOrderPicking){ 
				//Will be good when the thread loaded from this JVM process cache.
				//Also good when there is a RAM based DAO implementation
				
				supplyOrderPickingDaoOf(userContext).planToRemoveSupplyOrderListWithProcessing(supplyOrderPicking, processingId, this.emptyOptions());

				supplyOrderPicking = saveSupplyOrderPicking(userContext, supplyOrderPicking, tokens().withSupplyOrderList().done());
				return supplyOrderPicking;
			}
	}
	//disconnect SupplyOrderPicking with shipment in SupplyOrder
	protected SupplyOrderPicking breakWithSupplyOrderByShipment(RetailscmUserContext userContext, String supplyOrderPickingId, String shipmentId,  String [] tokensExpr)
		 throws Exception{
			
			//TODO add check code here
			
			SupplyOrderPicking supplyOrderPicking = loadSupplyOrderPicking(userContext, supplyOrderPickingId, allTokens());

			synchronized(supplyOrderPicking){ 
				//Will be good when the thread loaded from this JVM process cache.
				//Also good when there is a RAM based DAO implementation
				
				supplyOrderPickingDaoOf(userContext).planToRemoveSupplyOrderListWithShipment(supplyOrderPicking, shipmentId, this.emptyOptions());

				supplyOrderPicking = saveSupplyOrderPicking(userContext, supplyOrderPicking, tokens().withSupplyOrderList().done());
				return supplyOrderPicking;
			}
	}
	//disconnect SupplyOrderPicking with delivery in SupplyOrder
	protected SupplyOrderPicking breakWithSupplyOrderByDelivery(RetailscmUserContext userContext, String supplyOrderPickingId, String deliveryId,  String [] tokensExpr)
		 throws Exception{
			
			//TODO add check code here
			
			SupplyOrderPicking supplyOrderPicking = loadSupplyOrderPicking(userContext, supplyOrderPickingId, allTokens());

			synchronized(supplyOrderPicking){ 
				//Will be good when the thread loaded from this JVM process cache.
				//Also good when there is a RAM based DAO implementation
				
				supplyOrderPickingDaoOf(userContext).planToRemoveSupplyOrderListWithDelivery(supplyOrderPicking, deliveryId, this.emptyOptions());

				supplyOrderPicking = saveSupplyOrderPicking(userContext, supplyOrderPicking, tokens().withSupplyOrderList().done());
				return supplyOrderPicking;
			}
	}
	
	
	
	
	

	protected void checkParamsForAddingSupplyOrder(RetailscmUserContext userContext, String supplyOrderPickingId, String buyerId, String sellerId, String title, BigDecimal totalAmount, String confirmationId, String approvalId, String processingId, String shipmentId, String deliveryId,String [] tokensExpr) throws Exception{
		
				checkerOf(userContext).checkIdOfSupplyOrderPicking(supplyOrderPickingId);

		
		checkerOf(userContext).checkBuyerIdOfSupplyOrder(buyerId);
		
		checkerOf(userContext).checkSellerIdOfSupplyOrder(sellerId);
		
		checkerOf(userContext).checkTitleOfSupplyOrder(title);
		
		checkerOf(userContext).checkTotalAmountOfSupplyOrder(totalAmount);
		
		checkerOf(userContext).checkConfirmationIdOfSupplyOrder(confirmationId);
		
		checkerOf(userContext).checkApprovalIdOfSupplyOrder(approvalId);
		
		checkerOf(userContext).checkProcessingIdOfSupplyOrder(processingId);
		
		checkerOf(userContext).checkShipmentIdOfSupplyOrder(shipmentId);
		
		checkerOf(userContext).checkDeliveryIdOfSupplyOrder(deliveryId);
	
		checkerOf(userContext).throwExceptionIfHasErrors(SupplyOrderPickingManagerException.class);

	
	}
	public  SupplyOrderPicking addSupplyOrder(RetailscmUserContext userContext, String supplyOrderPickingId, String buyerId, String sellerId, String title, BigDecimal totalAmount, String confirmationId, String approvalId, String processingId, String shipmentId, String deliveryId, String [] tokensExpr) throws Exception
	{	
		
		checkParamsForAddingSupplyOrder(userContext,supplyOrderPickingId,buyerId, sellerId, title, totalAmount, confirmationId, approvalId, processingId, shipmentId, deliveryId,tokensExpr);
		
		SupplyOrder supplyOrder = createSupplyOrder(userContext,buyerId, sellerId, title, totalAmount, confirmationId, approvalId, processingId, shipmentId, deliveryId);
		
		SupplyOrderPicking supplyOrderPicking = loadSupplyOrderPicking(userContext, supplyOrderPickingId, allTokens());
		synchronized(supplyOrderPicking){ 
			//Will be good when the supplyOrderPicking loaded from this JVM process cache.
			//Also good when there is a RAM based DAO implementation
			supplyOrderPicking.addSupplyOrder( supplyOrder );		
			supplyOrderPicking = saveSupplyOrderPicking(userContext, supplyOrderPicking, tokens().withSupplyOrderList().done());
			
			userContext.getManagerGroup().getSupplyOrderManager().onNewInstanceCreated(userContext, supplyOrder);
			return present(userContext,supplyOrderPicking, mergedAllTokens(tokensExpr));
		}
	}
	protected void checkParamsForUpdatingSupplyOrderProperties(RetailscmUserContext userContext, String supplyOrderPickingId,String id,String title,BigDecimal totalAmount,String [] tokensExpr) throws Exception {
		
		checkerOf(userContext).checkIdOfSupplyOrderPicking(supplyOrderPickingId);
		checkerOf(userContext).checkIdOfSupplyOrder(id);
		
		checkerOf(userContext).checkTitleOfSupplyOrder( title);
		checkerOf(userContext).checkTotalAmountOfSupplyOrder( totalAmount);

		checkerOf(userContext).throwExceptionIfHasErrors(SupplyOrderPickingManagerException.class);
		
	}
	public  SupplyOrderPicking updateSupplyOrderProperties(RetailscmUserContext userContext, String supplyOrderPickingId, String id,String title,BigDecimal totalAmount, String [] tokensExpr) throws Exception
	{	
		checkParamsForUpdatingSupplyOrderProperties(userContext,supplyOrderPickingId,id,title,totalAmount,tokensExpr);

		Map<String, Object> options = tokens()
				.allTokens()
				//.withSupplyOrderListList()
				.searchSupplyOrderListWith(SupplyOrder.ID_PROPERTY, "is", id).done();
		
		SupplyOrderPicking supplyOrderPickingToUpdate = loadSupplyOrderPicking(userContext, supplyOrderPickingId, options);
		
		if(supplyOrderPickingToUpdate.getSupplyOrderList().isEmpty()){
			throw new SupplyOrderPickingManagerException("SupplyOrder is NOT FOUND with id: '"+id+"'");
		}
		
		SupplyOrder item = supplyOrderPickingToUpdate.getSupplyOrderList().first();
		
		item.updateTitle( title );
		item.updateTotalAmount( totalAmount );

		
		//checkParamsForAddingSupplyOrder(userContext,supplyOrderPickingId,name, code, used,tokensExpr);
		SupplyOrderPicking supplyOrderPicking = saveSupplyOrderPicking(userContext, supplyOrderPickingToUpdate, tokens().withSupplyOrderList().done());
		synchronized(supplyOrderPicking){ 
			return present(userContext,supplyOrderPicking, mergedAllTokens(tokensExpr));
		}
	}
	
	
	protected SupplyOrder createSupplyOrder(RetailscmUserContext userContext, String buyerId, String sellerId, String title, BigDecimal totalAmount, String confirmationId, String approvalId, String processingId, String shipmentId, String deliveryId) throws Exception{

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
		SupplyOrderShipment  shipment = new SupplyOrderShipment();
		shipment.setId(shipmentId);		
		supplyOrder.setShipment(shipment);		
		SupplyOrderDelivery  delivery = new SupplyOrderDelivery();
		delivery.setId(deliveryId);		
		supplyOrder.setDelivery(delivery);		
		supplyOrder.setLastUpdateTime(userContext.now());
	
		
		return supplyOrder;
	
		
	}
	
	protected SupplyOrder createIndexedSupplyOrder(String id, int version){

		SupplyOrder supplyOrder = new SupplyOrder();
		supplyOrder.setId(id);
		supplyOrder.setVersion(version);
		return supplyOrder;			
		
	}
	
	protected void checkParamsForRemovingSupplyOrderList(RetailscmUserContext userContext, String supplyOrderPickingId, 
			String supplyOrderIds[],String [] tokensExpr) throws Exception {
		
<<<<<<< HEAD
		userContext.getChecker().checkIdOfSupplyOrderPicking(supplyOrderPickingId);
		for(String supplyOrderIdItem: supplyOrderIds){
			userContext.getChecker().checkIdOfSupplyOrder(supplyOrderIdItem);
=======
		checkerOf(userContext).checkIdOfSupplyOrderPicking(supplyOrderPickingId);
		for(String supplyOrderIdItem: supplyOrderIds){
			checkerOf(userContext).checkIdOfSupplyOrder(supplyOrderIdItem);
>>>>>>> ea67698ef1c4e94c89147baaf9f93aa768973fbe
		}
		
		checkerOf(userContext).throwExceptionIfHasErrors(SupplyOrderPickingManagerException.class);
		
	}
	public  SupplyOrderPicking removeSupplyOrderList(RetailscmUserContext userContext, String supplyOrderPickingId, 
			String supplyOrderIds[],String [] tokensExpr) throws Exception{
			
			checkParamsForRemovingSupplyOrderList(userContext, supplyOrderPickingId,  supplyOrderIds, tokensExpr);
			
			
			SupplyOrderPicking supplyOrderPicking = loadSupplyOrderPicking(userContext, supplyOrderPickingId, allTokens());
			synchronized(supplyOrderPicking){ 
				//Will be good when the supplyOrderPicking loaded from this JVM process cache.
				//Also good when there is a RAM based DAO implementation
				supplyOrderPickingDaoOf(userContext).planToRemoveSupplyOrderList(supplyOrderPicking, supplyOrderIds, allTokens());
				supplyOrderPicking = saveSupplyOrderPicking(userContext, supplyOrderPicking, tokens().withSupplyOrderList().done());
				deleteRelationListInGraph(userContext, supplyOrderPicking.getSupplyOrderList());
				return present(userContext,supplyOrderPicking, mergedAllTokens(tokensExpr));
			}
	}
	
	protected void checkParamsForRemovingSupplyOrder(RetailscmUserContext userContext, String supplyOrderPickingId, 
		String supplyOrderId, int supplyOrderVersion,String [] tokensExpr) throws Exception{
		
		checkerOf(userContext).checkIdOfSupplyOrderPicking( supplyOrderPickingId);
		checkerOf(userContext).checkIdOfSupplyOrder(supplyOrderId);
		checkerOf(userContext).checkVersionOfSupplyOrder(supplyOrderVersion);
		checkerOf(userContext).throwExceptionIfHasErrors(SupplyOrderPickingManagerException.class);
	
	}
	public  SupplyOrderPicking removeSupplyOrder(RetailscmUserContext userContext, String supplyOrderPickingId, 
		String supplyOrderId, int supplyOrderVersion,String [] tokensExpr) throws Exception{
		
		checkParamsForRemovingSupplyOrder(userContext,supplyOrderPickingId, supplyOrderId, supplyOrderVersion,tokensExpr);
		
		SupplyOrder supplyOrder = createIndexedSupplyOrder(supplyOrderId, supplyOrderVersion);
		SupplyOrderPicking supplyOrderPicking = loadSupplyOrderPicking(userContext, supplyOrderPickingId, allTokens());
		synchronized(supplyOrderPicking){ 
			//Will be good when the supplyOrderPicking loaded from this JVM process cache.
			//Also good when there is a RAM based DAO implementation
			supplyOrderPicking.removeSupplyOrder( supplyOrder );		
			supplyOrderPicking = saveSupplyOrderPicking(userContext, supplyOrderPicking, tokens().withSupplyOrderList().done());
			deleteRelationInGraph(userContext, supplyOrder);
			return present(userContext,supplyOrderPicking, mergedAllTokens(tokensExpr));
		}
		
		
	}
	protected void checkParamsForCopyingSupplyOrder(RetailscmUserContext userContext, String supplyOrderPickingId, 
		String supplyOrderId, int supplyOrderVersion,String [] tokensExpr) throws Exception{
		
		checkerOf(userContext).checkIdOfSupplyOrderPicking( supplyOrderPickingId);
		checkerOf(userContext).checkIdOfSupplyOrder(supplyOrderId);
		checkerOf(userContext).checkVersionOfSupplyOrder(supplyOrderVersion);
		checkerOf(userContext).throwExceptionIfHasErrors(SupplyOrderPickingManagerException.class);
	
	}
	public  SupplyOrderPicking copySupplyOrderFrom(RetailscmUserContext userContext, String supplyOrderPickingId, 
		String supplyOrderId, int supplyOrderVersion,String [] tokensExpr) throws Exception{
		
		checkParamsForCopyingSupplyOrder(userContext,supplyOrderPickingId, supplyOrderId, supplyOrderVersion,tokensExpr);
		
		SupplyOrder supplyOrder = createIndexedSupplyOrder(supplyOrderId, supplyOrderVersion);
		SupplyOrderPicking supplyOrderPicking = loadSupplyOrderPicking(userContext, supplyOrderPickingId, allTokens());
		synchronized(supplyOrderPicking){ 
			//Will be good when the supplyOrderPicking loaded from this JVM process cache.
			//Also good when there is a RAM based DAO implementation
			
			supplyOrder.updateLastUpdateTime(userContext.now());
			
			supplyOrderPicking.copySupplyOrderFrom( supplyOrder );		
			supplyOrderPicking = saveSupplyOrderPicking(userContext, supplyOrderPicking, tokens().withSupplyOrderList().done());
			
			userContext.getManagerGroup().getSupplyOrderManager().onNewInstanceCreated(userContext, (SupplyOrder)supplyOrderPicking.getFlexiableObjects().get(BaseEntity.COPIED_CHILD));
			return present(userContext,supplyOrderPicking, mergedAllTokens(tokensExpr));
		}
		
	}
	
	protected void checkParamsForUpdatingSupplyOrder(RetailscmUserContext userContext, String supplyOrderPickingId, String supplyOrderId, int supplyOrderVersion, String property, String newValueExpr,String [] tokensExpr) throws Exception{
		

		
		checkerOf(userContext).checkIdOfSupplyOrderPicking(supplyOrderPickingId);
		checkerOf(userContext).checkIdOfSupplyOrder(supplyOrderId);
		checkerOf(userContext).checkVersionOfSupplyOrder(supplyOrderVersion);
		

		if(SupplyOrder.TITLE_PROPERTY.equals(property)){
			checkerOf(userContext).checkTitleOfSupplyOrder(parseString(newValueExpr));
		}
		
		if(SupplyOrder.TOTAL_AMOUNT_PROPERTY.equals(property)){
			checkerOf(userContext).checkTotalAmountOfSupplyOrder(parseBigDecimal(newValueExpr));
		}
		
	
		checkerOf(userContext).throwExceptionIfHasErrors(SupplyOrderPickingManagerException.class);
	
	}
	
	public  SupplyOrderPicking updateSupplyOrder(RetailscmUserContext userContext, String supplyOrderPickingId, String supplyOrderId, int supplyOrderVersion, String property, String newValueExpr,String [] tokensExpr)
			throws Exception{
		
		checkParamsForUpdatingSupplyOrder(userContext, supplyOrderPickingId, supplyOrderId, supplyOrderVersion, property, newValueExpr,  tokensExpr);
		
		Map<String,Object> loadTokens = this.tokens().withSupplyOrderList().searchSupplyOrderListWith(SupplyOrder.ID_PROPERTY, "eq", supplyOrderId).done();
		
		
		
		SupplyOrderPicking supplyOrderPicking = loadSupplyOrderPicking(userContext, supplyOrderPickingId, loadTokens);
		
		synchronized(supplyOrderPicking){ 
			//Will be good when the supplyOrderPicking loaded from this JVM process cache.
			//Also good when there is a RAM based DAO implementation
			//supplyOrderPicking.removeSupplyOrder( supplyOrder );	
			//make changes to AcceleraterAccount.
			SupplyOrder supplyOrderIndex = createIndexedSupplyOrder(supplyOrderId, supplyOrderVersion);
		
			SupplyOrder supplyOrder = supplyOrderPicking.findTheSupplyOrder(supplyOrderIndex);
			if(supplyOrder == null){
				throw new SupplyOrderPickingManagerException(supplyOrder+" is NOT FOUND" );
			}
			
			supplyOrder.changeProperty(property, newValueExpr);
			supplyOrder.updateLastUpdateTime(userContext.now());
			supplyOrderPicking = saveSupplyOrderPicking(userContext, supplyOrderPicking, tokens().withSupplyOrderList().done());
			return present(userContext,supplyOrderPicking, mergedAllTokens(tokensExpr));
		}

	}
	/*

	*/
	



	public void onNewInstanceCreated(RetailscmUserContext userContext, SupplyOrderPicking newCreated) throws Exception{
		ensureRelationInGraph(userContext, newCreated);
		sendCreationEvent(userContext, newCreated);
	}

}


