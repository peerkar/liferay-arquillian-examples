package fi.soveltia.arquillian.service;

import org.osgi.service.component.annotations.Component;

@Component(
		immediate = true, 
		service = SampleService.class
)
public class SampleServiceImpl implements SampleService {

	@Override
	public int add(int a, int b) {
		return a + b;
	}
}
