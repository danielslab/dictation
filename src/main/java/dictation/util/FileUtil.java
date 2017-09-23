package dictation.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.springframework.core.io.ClassPathResource;

public class FileUtil {
	public static String readFile(String path) throws IOException {
		try (InputStream is = new ClassPathResource(path).getInputStream()) {
			BufferedReader br = new BufferedReader(new InputStreamReader(is));
			String buf;
			StringBuilder result = new StringBuilder();
			while((buf = br.readLine()) != null) {
				result.append(buf).append(System.lineSeparator());
			}

			// 最後の改行を削除する。
			result = result.delete(result.length() - System.lineSeparator().length(), result.length());
			return result.toString();
		}
	}
}
