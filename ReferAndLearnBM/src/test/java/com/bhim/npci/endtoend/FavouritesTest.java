package com.bhim.npci.endtoend;

import org.testng.annotations.Test;

import com.bhim.npci.genericutility.BaseClass;
/**
 * @author Priyanka
 * This class contains the methods associated with favourites module
 */
public class FavouritesTest extends BaseClass {

	/**
	 * Profile->Favourites->AddContact
	 */
	@Test
	public void addingFavourites() {
		home.clickOnProfileMenuAndVerify()
			.clickOnFavouritesAndVerify()
			.searchAndSelectContact()
			.verifyAddToFavouriteDialogueBoxAndAddFavourite()
			.backNavigation();
	}
}
