import org.junit.Before;

import com.fountainhead.client.presenter.SignInPagePresenter;
import com.google.gwt.event.shared.EventBus;

public class FirstPresenterTest {

	private EventBus eventBus;
	private SignInPagePresenter.MyView view;
	private SignInPagePresenter presenter;

	@Before
	public void setUp() throws Exception {
		// final Injector injector = Guice.createInjector(new ClientModule());
		//
		// // get references to mock objects to verify interaction during test
		// eventBus = injector.getInstance(EventBus.class);
		//
		// view = injector.getInstance(ExitPagePresenter.Display.class);
		//
		// // presenter construction will call bind() which may call view and
		// // eventBus (so put into replay now).
		// replay(view, eventBus);
		//
		// presenter = injector.getInstance(ExitPagePresenter.class);
		//
		// // Verify calls to mock objects were as expected.
		// verify(eventBus, view);
		//
		// // Reset so tests can defined their own expectations.
		// resetToStrict(eventBus, view);
	}

}