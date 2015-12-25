package org.hisrc.jsonix.tests.issues;

import java.io.File;
import java.net.URL;

import org.junit.Before;
import org.junit.Test;

import com.sun.codemodel.JCodeModel;
import com.sun.tools.xjc.ConsoleErrorReporter;
import com.sun.tools.xjc.ModelLoader;
import com.sun.tools.xjc.Options;
import com.sun.tools.xjc.model.Model;

public class CompileIssues {

	@Before
	public void setUp() {
		System.setProperty("javax.xml.accessExternalSchema", "all");
	}

	@Test
	public void compilesIssues() throws Exception {

		new File("target/generated-sources/xjc").mkdirs();

		URL schema = getClass().getResource("/schema.xsd");
		URL binding = getClass().getResource("/binding.xjb");

		final String[] arguments = new String[] {
				"-xmlschema", schema.toExternalForm(),
				"-b", binding.toExternalForm(),
				"-d", "target/generated-sources/xjc",
				"-extension",
				"-Xjsonix"

		};

		Options options = new Options();
		options.parseArguments(arguments);
		ConsoleErrorReporter receiver = new ConsoleErrorReporter();
		Model model = ModelLoader.load(options, new JCodeModel(), receiver);
		model.generateCode(options, receiver);
		com.sun.codemodel.CodeWriter cw = options.createCodeWriter();
		model.codeModel.build(cw);
	}
}
