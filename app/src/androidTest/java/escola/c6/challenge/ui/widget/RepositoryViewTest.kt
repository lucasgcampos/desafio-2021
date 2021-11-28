package escola.c6.challenge.ui.widget

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.platform.app.InstrumentationRegistry
import escola.c6.challenge.R
import escola.c6.challenge.launch
import escola.c6.challenge.network.model.OwnerResponse
import escola.c6.challenge.network.model.RepositoryResponse
import org.junit.Test

class RepositoryViewTest {

    @Test
    fun whenSetupCustomView_shouldDisplayInformation() {
        // given
        val repository = createRepositoryResponse()
        val customView = RepositoryView(InstrumentationRegistry.getInstrumentation().targetContext)

        // when
        customView.launch()
        customView.setup(repository)

        // then
        onView(withId(R.id.txt_repository_name)).check(matches(withText(repository.name)))
        onView(withId(R.id.txt_repository_description)).check(matches(withText("Description: ${repository.description}")))
        onView(withId(R.id.txt_author)).check(matches(withText("Author: ${repository.owner.login}")))
    }
}

fun createRepositoryResponse() = RepositoryResponse(
    name = "My project 2021",
    description = "some description about the repository",
    forks = 10,
    stars = 6,
    owner = OwnerResponse(
        login = "Lucas Campos",
        avatar = ""
    )
)
