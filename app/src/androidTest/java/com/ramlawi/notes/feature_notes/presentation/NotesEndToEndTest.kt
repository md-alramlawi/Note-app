package com.ramlawi.notes.feature_notes.presentation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.ramlawi.notes.core.util.TestTags
import com.ramlawi.notes.di.AppModule
import com.ramlawi.notes.feature_notes.presentation.add_edit_note.AddEditNoteScreen
import com.ramlawi.notes.feature_notes.presentation.notes.NotesScreen
import com.ramlawi.notes.feature_notes.presentation.util.Screen
import com.ramlawi.notes.ui.theme.NoteAppTheme
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.UninstallModules
import org.junit.Before
import org.junit.Rule
import org.junit.Test

/**
 * Created by Mohammed Alramlawi on 1/22/2022.
 */

@HiltAndroidTest
@UninstallModules(AppModule::class)

class NotesEndToEndTest {

    @get:Rule(order = 0)
    val hiltRule = HiltAndroidRule(this)

    @get:Rule(order = 1)
    val composeRule = createAndroidComposeRule<MainActivity>()

    @ExperimentalAnimationApi
    @Before
    fun setUp() {
        hiltRule.inject()
        composeRule.setContent {
            NoteAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {

                    val navController = rememberNavController()
                    NavHost(
                        navController = navController,
                        startDestination = Screen.NotesScreen.route
                    ){
                        composable(route = Screen.NotesScreen.route){
                            NotesScreen(navController = navController)
                        }

                        composable(route = Screen.AddEditNoteScreen.route +
                                "?noteId={noteId}&noteColor={noteColor}",
                            arguments = listOf(
                                navArgument(
                                    name = "noteId"
                                ) {
                                    type = NavType.IntType
                                    defaultValue = -1
                                },
                                navArgument(
                                    name = "noteColor"
                                ) {
                                    type = NavType.IntType
                                    defaultValue = -1
                                }
                            )
                        ) {
                            val color = it.arguments?.getInt("noteColor") ?: -1
                            AddEditNoteScreen(navController = navController, noteColor = color)
                        }
                    }

                }
            }
        }
    }

    @Test
    fun saveNewNote_editAfterwards(){

        // click add button
        composeRule.onNodeWithContentDescription("Add note").performClick()

        // enter title and content
        composeRule.onNodeWithTag(TestTags.TITLE_TEXT_FILED)
            .performTextInput("test_title")
        composeRule.onNodeWithTag(TestTags.CONTENT_TEXT_FIELD)
            .performTextInput("test_content")
        // click save
        composeRule.onNodeWithContentDescription("Save note").performClick()

        // check that the note just added exist
        composeRule.onNodeWithText("test_title").assertIsDisplayed()
        // click on recently added note
        composeRule.onNodeWithText("test_title").performClick()

        // check that note title and content contains our entered data
        composeRule.onNodeWithTag(TestTags.TITLE_TEXT_FILED).assertTextEquals("test_title")
        composeRule.onNodeWithTag(TestTags.CONTENT_TEXT_FIELD).assertTextEquals("test_content")
        // add 2 to title
        composeRule.onNodeWithTag(TestTags.TITLE_TEXT_FILED).performTextInput("2")
        // save note again
        composeRule.onNodeWithContentDescription("Save note").performClick()

        // check that the note just added exist
        composeRule.onNodeWithText("test_title2").assertIsDisplayed()

    }

    @Test
    fun saveNote_orderByTitleDescending(){
        for(i in 1..3){
            // click add button
            composeRule.onNodeWithContentDescription("Add note").performClick()

            // enter title and content
            composeRule.onNodeWithTag(TestTags.TITLE_TEXT_FILED)
                .performTextInput(i.toString())
            composeRule.onNodeWithTag(TestTags.CONTENT_TEXT_FIELD)
                .performTextInput(i.toString())
            // click save
            composeRule.onNodeWithContentDescription("Save note").performClick()
        }

        composeRule.onNodeWithText("1").assertIsDisplayed()
        composeRule.onNodeWithText("2").assertIsDisplayed()
        composeRule.onNodeWithText("3").assertIsDisplayed()

        composeRule.onNodeWithContentDescription("Sort").performClick()
        composeRule.onNodeWithContentDescription("Title").performClick()
        composeRule.onNodeWithContentDescription("Descending").performClick()

        composeRule.onAllNodesWithTag(TestTags.NOTE_ITEM)[0]
            .assertTextContains("3")
        composeRule.onAllNodesWithTag(TestTags.NOTE_ITEM)[1]
            .assertTextContains("2")
        composeRule.onAllNodesWithTag(TestTags.NOTE_ITEM)[2]
            .assertTextContains("1")
    }
}