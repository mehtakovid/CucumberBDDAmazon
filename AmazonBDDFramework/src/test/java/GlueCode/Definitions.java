package GlueCode;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class Definitions extends BaseClass {

	public static final Logger log = Logger.getLogger(Definitions.class);
	
	
	@Given("^Website is up and running$")
	public boolean WebsiteIsUpandRunning() {
		driver.get("https://www.amazon.in/");
		driver.manage().window().maximize();
		if(wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='nav-logo']"))).isDisplayed()) {
			log.info("Website is up and Running.");
			return true;
		}else {
			log.fatal("Connection to Website failed.");
			return false;
		}
	}
	
	@Given("^User is on Amazon Home Page as a Logged in User$")
	public void LoggedInUserCheck() {
		if(driver.findElement(By.xpath("//span[contains(text(),'Hello, Kovid')]")).isDisplayed()) {
			log.info("User logged in.");
		}else {
			log.warn("User not logged in. Retrying.");
			SignInOption();
			EnterCreds("kovidmehta10@gmail.com", "honeybees@93");
		}
	}
	
	@Given("^Product Search bar is present$")
	public void search_bar_is_present() {
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='twotabsearchtextbox']")));
	}
	
	@Given("^User selects any product from the Home Page$")
	public void SelectProductFromReleatedItems() throws InterruptedException {
		Actions action = new Actions(driver);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(text(),'Related to')]")));
		action.moveToElement(driver.findElement(By.xpath("//*[contains(text(),'Related to')]"))).build().perform();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@class='a-section a-spacing-none feed-carousel hovering']/descendant::li"))).click();
	}
	
	@Given("^Redirects to New tab opened$")
	public void SwitchWindow() {
		Set<String> handles = driver.getWindowHandles();
		for(String newWin:handles) {
			driver.switchTo().window(newWin);
		}
	}
	
	@Given("^User is on My Cart Page$")
	public void MyCart() {
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@id='nav-cart']"))).click();
	}
	
	@Given("^User can click on SignIn Option under Accounts and Lists Widget$")
	public static void SignInOption() {
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@id='nav-link-accountList']")));
		Actions action = new Actions(driver);
		action.moveToElement(driver.findElement(By.xpath("//a[@id='nav-link-accountList']"))).click().build().perform();
		if(driver.findElements(By.xpath("//input[@type='email']")).isEmpty()) {
			action.moveToElement(driver.findElement(By.xpath("//a[@id='nav-link-accountList']"))).moveToElement(driver.findElement(By.xpath("//a[@class='nav-action-button'][@rel='nofollow']//span[text()='Sign in'][@class='nav-action-inner']"))).click().build().perform();	
			log.info("Sign in successful.");
		}
			}
	
	@Given("^User is on Wishlist Page$")
	public void WishListPage() {
		Actions action = new Actions(driver);
		action.moveToElement(wait.until(ExpectedConditions.visibilityOfElementLocated
				(By.xpath("//a[@id='nav-link-accountList']")))).moveToElement(wait.until(ExpectedConditions.visibilityOfElementLocated
						(By.xpath("//span[text()='Your Account']/parent::a/following-sibling::a//span[text()='Your Wish List']")))).click().build().perform();
		
	}
	
	
	@When("^Product List should be displayed$")
	public boolean product_List_should_be_displayed() {
	    String SearchResult = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='a-section a-spacing-small a-spacing-top-small']"))).getText();
	    if(SearchResult.contains("over")) {
	    	log.info("Product List Displayed.");
	    	return true;
	    }
	    else {
	    	log.fatal("Products not displayed as per the search.");
	    	return false;
	    }
	} 

	@When("^User Enter \"(.*)\" and \"(.*)\" as credentials$")
	public static void EnterCreds(String Username, String Password) {
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@type='email']"))).sendKeys(Username);
		driver.findElement(By.xpath("//input[@id='continue']")).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@type='password']"))).sendKeys(Password);
		driver.findElement(By.xpath("//input[@id='signInSubmit']")).click();
	}
	
	@When("^Click on Orders and Check if there are any open orders$")
	public void checkOpenOrder() {
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='Orders']"))).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[contains(text(),'Open Orders')]"))).click();	
	}
	
	@When("^User clicks on Order widget$")
	public void OrderWidget() {
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='Orders']"))).click();
	}
	
	@When("^User searches for Today Deals Section$")
	public void SearchForTodayDeals() {
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[contains(text(),'Today')]"))).isDisplayed();
		}
	
	@When("^User is able to see items added in the Cart$")
	public boolean ItemsinCartCheck() {
		List<WebElement> CartEmpty = driver.findElements(By.xpath("//h2[contains(text(),'Your Shopping Cart is empty.')]"));
		if(CartEmpty.isEmpty()) {
			log.warn("Cart is empty. Test Case will fail.");
			return false;
		}
		else {
			log.info("Items found in cart. Test Case will proceed.");
			return true;
		}
	}
	
	@When("^User clicks on Ship By Category and Navigate to particular product$")
	public boolean ShopByCategory() throws InterruptedException {
		Actions action = new Actions(driver);
		action.moveToElement(wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@id='nav-link-shopall']")))).moveToElement(wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='Mobiles, Computers']")))).click().build().perform();
		if(!driver.findElements(By.xpath("//h1[text()='Wearable Devices']")).isEmpty()) {
			log.info("User is able to traverse to Products through category.");
			return true;
		}else {
			log.fatal("User not able to traverse to products through category.");
			return false;
		}
	}
	
	
	@When("^User go to Accounts under Accounts and List widget and Click Amazon Pay$")
	public void SelectYourAccounts() {
		Actions action = new Actions(driver);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@id='nav-link-accountList']")));
		action.moveToElement(driver.findElement(By.xpath("//a[@id='nav-link-accountList']"))).moveToElement(driver.findElement(By.xpath("//span[text()='Your Account']"))).click().build().perform();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h2[contains(text(),'Amazon Pay balance')]"))).click();
	}
	
	@When("^User go to Accounts under Accounts and List Widget and Click Prime$")
	public void AmazonPrime() {
		Actions action = new Actions(driver);
		action.moveToElement(driver.findElement(By.xpath("//a[@id='nav-link-accountList']"))).moveToElement(driver.findElement(By.xpath("//span[text()='Your Account']"))).click().build().perform();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h2[contains(text(),'Prime')]"))).click();
	}
	
	@When("^Clicks on Manage Amazon Pay Balance to view Statement$")
	public void ManageAmazonPay() throws InterruptedException {
		driver.findElement(By.xpath("//span[contains(text(),'Manage Amazon Pay balance')]")).click();
		Thread.sleep(4000);
		if(!driver.findElements(By.xpath("//span[contains(text(),'View Statement')]")).isEmpty()) {
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[contains(text(),'View Statement')]"))).click();
		}
		else {
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[contains(text(),'Your Transaction')]"))).click();
		}
		}
	
	
	@When("^User clicks on Buy Now on Product page$")
	public void BuyNowClick() {
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='buy-now-button']"))).click();
	}
	
	@When("^Selects Anyone Delivery Option$")
	public void DeliveryAddress() {
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//li[text()='6A 201 Kalpataru Serenity']/ancestor::div[@id='address-book-entry-0']/descendant::a[contains(text(),'Deliver')]"))).click();
	}
	
	@When("^User clicks on Add to Cart on Wishlist page$")
	public boolean AddWishListItemtoCart() {
		if(!wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[contains(text(),'Add to Cart')]"))).isDisplayed()) {
			System.out.println("No items in wishlist.");
			return false;
		}else {
			//System.out.println("Item to be added to cart : " +driver.findElements(By.xpath("//a[contains(text(),'Add to Cart')]")).get(0).getText());
			List<WebElement> AllAddtoCart = driver.findElements(By.xpath("//a[contains(text(),'Add to Cart')]"));
			AllAddtoCart.get(0).click();
			return true;
		}
	}
	
	@When("^User goes to My Cart Page$")
	public void MyCartwhen() {
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@id='nav-cart']"))).click();
	}
	
	
	@When("^User clicks on Create a List and Enter a List Name$")
	public void CreateAWishlist(DataTable ListName) throws InterruptedException {
		List<String> data = ListName.asList();
		driver.findElement(By.xpath("//a[text()='Create a List']")).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@name='create-name']"))).clear();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@name='create-name']"))).sendKeys(data.get(0));
		Thread.sleep(10000);
		JavascriptExecutor jse = ((JavascriptExecutor)driver);
		jse.executeScript("arguments[0].click();", driver.findElement(By.xpath("//span[contains(text(),'Create List')]")));
		
	}
	
	@When("^User clicks on Manage List and Clicks on Delete List$")
	public void DeleteWishlist() throws InterruptedException {
		Actions action = new Actions(driver);
		action.moveToElement(driver.findElement(By.xpath("//a[@id='overflow-menu-popover-trigger']"))).moveToElement(driver.findElement(By.xpath("//a[text()='Manage list']"))).click().build().perform();
		Thread.sleep(5000);
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("arguments[0].scrollIntoView();", driver.findElement(By.xpath("//span[text()='Delete list']")));
		js.executeScript("arguments[0].click();", driver.findElement(By.xpath("//span[text()='Delete list']")));
		Thread.sleep(5000);
	}
	
		
	
	@When("User enters the Product Name in the Search bar and presses Enter")
	public void user_Enters_Product_Name(DataTable ProductName) {
		List<List<String>> data = ProductName.asLists();
	    driver.findElement(By.xpath("//input[@id='twotabsearchtextbox']")).sendKeys(data.get(0).get(0));
	    Actions action = new Actions(driver);
		action.moveToElement(driver.findElement(By.xpath("//input[@id='twotabsearchtextbox']"))).sendKeys(Keys.ENTER).build().perform();
	}

	
	@Then("Validate User Login")
	public void LoginVerification() {
		if(driver.findElement(By.xpath("//span[text()='Hello, Kovid']")).isDisplayed()) {
			log.info("Login Successfull.");
		}else if(driver.findElement(By.xpath("//h4[text()='There was a problem']")).isDisplayed()) {
			log.fatal("Login Failed.");
		}
	}

	@Then("Nth Product Should be fetched \"(.*)\" number")
	public void nth_Product_Should_be_fetched(String Number) {
		if(Integer.parseInt(Number)<=19) {
	    log.debug("Requested Search Result : " +driver.findElement(By.xpath("//div[@data-cel-widget='search_result_"+Number+"']/div")).getText());
		}
	    else {
	    	log.warn("Only 20 search results are shown on Page 1.");
	    }
	}
	
	@Then("Nth Product \"(.*)\" should be added to the cart")
	public void nth_ProductAddedtoCart(String Number) {
		if(Integer.parseInt(Number)<=19) {
	    driver.findElement(By.xpath("//div[@data-cel-widget='search_result_"+Number+"']/div/descendant::img")).click();
		}
	    else {
	    	log.warn("Operation to be performed only on first 20 search results.");
	    }
	}
	
	

	@Then("User should be able to see all the available Delivery options")
	public void ValidateDeliveryOptions() {
		List<String> AllOtherAddresses = new ArrayList<String>();
		List<String> MostRecentlyUsed = new ArrayList<String>();
		for(int i=0;i<5;i++) {
			 AllOtherAddresses.add(driver.findElement(By.xpath("//h2[text()='Other addresses']/parent::div/descendant::div[@id='address-book-entry-"+i+"']/div[1]")).getText());
		}
		for(int i=0;i<2;i++) {
			MostRecentlyUsed.add(driver.findElement(By.xpath("//h2[text()='Most recently used']/parent::div/descendant::div[@id='address-book-entry-"+i+"']/div[1]")).getText());
		}
		log.debug("First Two most Recently used Address : " +MostRecentlyUsed);
		log.debug("First 5 Addresses available are : " +AllOtherAddresses);
	}
	
	
	@Then("Add the Product to Wishlist")
	public void AddProductWishlist() throws InterruptedException {
		Thread.sleep(5000);
		Actions action = new Actions(driver);
		action.moveToElement(driver.findElement(By.xpath("//input[@id='add-to-wishlist-button-submit']"))).click().build().perform();
	}
	
	@Then("User should be able to fetch details of item using scroller")
	public void ScrollThroughTodayDeals(DataTable ItemNumber) throws InterruptedException {
		List<Integer> data = ItemNumber.asList(Integer.class);
		Thread.sleep(5000);
		Actions action = new Actions(driver);
		if(data.get(0)<=6) {
			String SearchedItem = driver.findElement(By.xpath("//span[contains(text(),'Today')]/parent::h2/parent::div/following-sibling::div/descendant::li['"+ItemNumber+"']")).getText();
			log.debug("Searched Item Details are as follows : " +SearchedItem);
		}else if(data.get(0)>6 && data.get(0)<=12) {
			action.moveToElement(driver.findElement(By.xpath("//span[contains(text(),'Today')]/parent::h2/parent::div/following-sibling::div/descendant::a[@aria-label='Carousel next slide']"))).click().build().perform();
			Thread.sleep(1000);
			String SearchedItem = driver.findElement(By.xpath("//span[contains(text(),'Today')]/parent::h2/parent::div/following-sibling::div/descendant::li["+ItemNumber+"]")).getText();
			log.debug("Searched Item Details are as follows : " +SearchedItem);
		}else if(data.get(0)>12 && data.get(0)<=15) {
			driver.findElement(By.xpath("//span[contains(text(),'Today')]/parent::h2/parent::div/following-sibling::div/descendant::a[@aria-label='Carousel next slide']")).click();
			String SearchedItem = driver.findElement(By.xpath("//span[contains(text(),'Today')]/parent::h2/parent::div/following-sibling::div/descendant::li["+ItemNumber+"]")).getText();
			log.debug("Searched Item Details are as follows : " +SearchedItem);
		}else {
			log.debug("Item Number should be between 1 to 15.");
		}
	}
	
	
	@Then("Select Item from Today's Deal")
	public void SelectItemFromTodayDeal() throws InterruptedException {
		Thread.sleep(5000);
		Actions action = new Actions(driver);
		action.moveToElement(driver.findElement(By.xpath("//span[contains(text(),'Deals')]/parent::h2/parent::div/following-sibling::div/div/descendant::li"))).build().perform();
		List<WebElement> TodayDeal = driver.findElements(By.xpath("//span[contains(text(),'Deals')]/parent::h2/parent::div/following-sibling::div/div/descendant::li"));
		TodayDeal.get(1).click();
	}
	
	
	@Then("User should be able to see current Amazon Pay Balance")
	public void FetchAmazonPayBalance() {	
		String AmazonBalance = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[contains(text(),'Amazon Pay Balance')]/following-sibling::span"))).getText();
		log.debug("Amazon Pay Balance is : " +AmazonBalance);
	}
	
	@Then("User should be able to see the expiry date of Prime Membership")
	public void PrimeMembership() {
		log.debug("Prime Membership Details : " +wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='primeCentralNextPaymentContainer']/div[2]"))).getText());
	}
	
	@Then("User should be able to click on Lightning Deals to see the deals")
	public void LigteningDeals() {
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[contains(text(),'Lightning Deals')]"))).click();
		List<WebElement> DealsOfTheDay = driver.findElements(By.xpath("//div[@class='a-section padCenterContainer aok-relative']//div[@id='widgetContent']/div"));
		if(DealsOfTheDay.isEmpty()) {
			log.info("Deals for Prime Member Present");
		}
	}
	
	@Then("User should be able to click on PrimeVideos")
	public void PrimeVideos() {
		driver.findElement(By.xpath("//a[contains(text(),'PrimeVideo.com')]")).click();
		log.debug("Prime Video Opened with following URL " +driver.getCurrentUrl());
	}
	
	@Then("User should be able to see different payment methods and Amount Options to add Amazon Balance")
	public void PaymentMethods() {
		List<WebElement> allPaymentMethods = driver.findElements(By.xpath("//span[contains(text(),'Your Saved Cards')]/parent::div/parent::div/following-sibling::div[@class='payment-row two-cells']/descendant::div[@class='card-info']"));
		log.debug("Following are the payment options available : "+allPaymentMethods);
		List<WebElement> AmountOptions = driver.findElements(By.xpath("//input[@name='selectAmount']"));
		log.debug("Following are the amount options available : " +AmountOptions);
	}
	
	@Then("Amazon Transactions should be displayed to the user")
	public void TransactionDetails() {
		List<WebElement> TransactionDetails = driver.findElements(By.xpath("//div[@id='content-section']/descendant::tr/td[2]"));
		log.debug("Transaction details are as follows : " +TransactionDetails);
	}
	
	
	@Then("User should be able to see the Product List")
	public void ProductsByCategory() {
		log.debug("Category of Products Displayed is : "+driver.findElement(By.xpath("//div[@class='bxw-pageheader__title__text']/h1")).getText());
	}
	
	
	@Then("User should be able to delete all the items in the cart")
	public boolean DeleteCartItems() throws InterruptedException {
		List<WebElement> AllItems = driver.findElements(By.xpath("//div[@data-name='Active Items']/descendant::input[@value='Delete']"));
		if(AllItems.isEmpty()) {
			log.fatal("No items in the cart. Test Case will fail.");
			return false;
		}else {
			System.out.println("Number of items in the cart : "+AllItems.size());
			for(WebElement ele : AllItems) {
				ele.click();
				Thread.sleep(4000);
				return true;
			}
		}
		return false;
	}
	
	@Then("User should be able to see Ticker Deals and Capture Screenshot")
	public void CaptureTickerDeals() throws IOException {
		List<WebElement> TickerDeals = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//div[@id='anonCarousel1']/descendant::li")));
		log.debug("Total Deals on Display : " +TickerDeals.size());
		
		for(int i=0; i<=TickerDeals.size(); i++) {
			File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(scrFile, new File("C:\\Users\\komehta\\eclipse-workspace\\CucumberFramework\\Ticker-"+i+".jpeg"));
			driver.findElement(By.xpath("//i[@class='a-icon a-icon-next-rounded']")).click();
		}
	}
	
	@Then("User should see Open order details if any open orders are present")
	public void openOrderCheck() {
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[contains(text(),'Open Orders')]"))).click();
		if(wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(text(),'Looking for an order? All of your orders have been dispatched.')]"))).isDisplayed()) {
			log.info("No Open Orders");
		}
		else {
			log.info("Open Orders are present. Kindly take a look.");
		}
	}
	
	@Then("User should be able to see order from entered year")
	public void OrderFromGivenYear(DataTable Year) {
		List<String> data = Year.asList();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@id='a-autoid-1']"))).click();
		Actions action = new Actions(driver);
		action.moveToElement(driver.findElement(By.xpath("//a[contains(text(),'"+data.get(0)+"')]"))).click().build().perform();
		List<WebElement> alldates = driver.findElements(By.xpath("//div[@class='a-row']/descendant::div[@class='a-column a-span3']/descendant::div[@class='a-row a-size-base']"));
		log.debug("Please find the Order dates of Year "+data.get(0)+" below : ");
		for(WebElement ele : alldates) {
			System.out.println(ele.getText());
		}
				
	}
	
	@Then("User should be able to list of Products containing Product Name")
	public boolean validateProductSearch(DataTable ProductName) {
		List<String> data = ProductName.asList();
		List<WebElement> SearchResult = driver.findElements(By.xpath("//div[@class='s-result-list s-search-results sg-row']//div[@class='sg-row']//descendant::div[@class='a-section a-spacing-none'][2]"));
		
		for(WebElement ele : SearchResult) {
			if(ele.getText().contains(data.get(0))) {
				return true;
			}
		}
		
		return false;
	}
	
		
	@Then("User traverses to My Cart from Home Page")
	public void OpenCart() {
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@id='nav-cart']"))).click();		
		
	}
	
	@Then("User should be able to increase the item quantity in cart")
	public void IncreaseItemQty() {
		driver.findElement(By.xpath("//span[@class='a-dropdown-prompt']")).click();
		driver.findElement(By.xpath("//li[@aria-checked='true']/following-sibling::li")).click();
		log.debug("New Quantity: " +wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@class='a-dropdown-prompt']"))).getText());
	}
	
	@Then("User should be able to decrease the item quantity in cart")
	public void DecreaseItemQty() {
		driver.findElement(By.xpath("//span[@class='a-dropdown-prompt']")).click();
		driver.findElement(By.xpath("//li[@aria-checked='true']/preceding-sibling::li[1]")).click();
		log.debug("New Quantity: " +wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@class='a-dropdown-prompt']"))).getText());
		
	}
	
	@Then("Clicks on Proceed to Buy after selecting the item")
	public void ProceedToBuy() {
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@name='proceedToCheckout']"))).click();
	}
	
	
	
	@Then("Click on Log Out to redirect on Sign In Page")
	public boolean ValidateUserLogOut() {
		Actions action = new Actions(driver);
		action.moveToElement(wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@id='nav-link-accountList']")))).moveToElement(driver.findElement(By.xpath("//span[text()='Sign Out']"))).click().build().perform();
		if(wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@type='email']"))).isDisplayed()) {
			return true;
		}else {
			return false;
		}
		
	}
	
	@Then("User should see Error Message stating Login Failed")
	public void LoginFailedValidation(DataTable ErrorMessage) {
		List<String> data = ErrorMessage.asList();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h4[contains(text(),'"+data.get(0)+"')]"))).isDisplayed();
	}
	
	@Then("User should be able to see all the payment methods")
	public void PaymentMethodsForPurchase() {
		List<WebElement> PaymentMethodsAvailableForPurchase = driver.findElements(By.xpath("//span[text()='Your saved credit and debit cards']/parent::div/parent::div/following-sibling::div/div/descendant::span[3]"));
		
		for(WebElement ele: PaymentMethodsAvailableForPurchase) {
			log.debug("Payment Method : " +ele.getText());
		}
		
	}
	
	@Then("Wishlist Item is added to cart")
	public boolean wishlistItwmIsAddedtoCart() {
		if(wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h4[contains(text(),'Added to Cart')]"))).isDisplayed()) {
			log.info("Wishlist Item added to cart.");
			return true;
		}else {
			log.fatal("Item not added to cart.");
			return false;
		}
	}
	
	@Then("User can see the new Shopping Wishlist")
	public boolean ValidateNewWishlist(DataTable ListName) throws InterruptedException {
		List<String> data = ListName.asList();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='your-lists-nav']//ul/li")));
		log.info("Moved to the Validation Part");
		List<WebElement> allWishlists = driver.findElements(By.xpath("//div[@id='your-lists-nav']//ul/li"));
		for(WebElement ele : allWishlists) {
			if(ele.getText().contains(data.get(0))) {
				return true;
			}
		}
		return false;
	}
	
	@Then("Add the item to Cart")
	public void AddProductToCard() throws InterruptedException {
		Thread.sleep(5000);
		Actions action = new Actions(driver);
		if(!driver.findElements(By.xpath("//input[@id='add-to-cart-button']")).isEmpty()) {
		action.moveToElement(driver.findElement(By.xpath("//input[@id='add-to-cart-button']"))).click().build().perform();
		}
		else {
			driver.findElement(By.xpath("//div[@id='mainResults']/ul/li[1]")).click();
			action.moveToElement(driver.findElement(By.xpath("//input[@id='add-to-cart-button']"))).click().build().perform();	
		}
	}
	
	@Then("User should see popup seeking confirmation")
	public boolean DeleteWishlistPopup() {
		if(driver.findElement(By.xpath("//h4[text()='Confirm delete']")).isDisplayed()) {
			return true;
		}
		return false;
	}
	
	@Then("Browser Should be closed post Test Case Completion")
	public void ExitFormalities() {
		driver.quit();
		driver=null;
		wait=null;
	}
	

}
