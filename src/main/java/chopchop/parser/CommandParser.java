// CommandParser.java

package chopchop.parser;

import java.util.List;
import java.util.HashMap;
import java.util.Optional;
import java.util.ArrayList;
import java.util.stream.Collectors;

import chopchop.util.StringView;

public class CommandParser {


	public Optional<CommandArguments> parse(String input) {

		var sv = new StringView(input);

		var x = new StringView("");
		var xs = new StringView("");

		sv.bisect(x, ' ', xs);

		/*
			add recipe NAME [/ingredient INGREDIENT [/qty QTY1]...]... [/step STEP]...
			delete recipe NAME
			find recipe KEYWORDS [MORE_KEYWORDS]
			list recipes

			add ingredient NAME [/qty QUANTITY] [/expiry DATE]
			delete ingredient NAME [/qty QUANTITY]
			find ingredient KEYWORDS [MORE_KEYWORDS]
			list ingredients
		*/

		var command = x.toString().strip();
		xs.bisect(x, ' ', xs);

		var target = Optional.of(x)
			.filter(s -> !s.isEmpty())
			.map(StringView::toString)
			.map(String::strip)
			.orElse(null);

		xs.bisect(x, '/', xs);
		var theRest = x.toString().strip();

		xs = xs.undrop(1);
		assert xs.at(0) == '/';

		var args = new HashMap<String, List<String>>();
		while (xs.size() > 0) {

			if (xs.find('/') != 0) {
				break;
			}

			var currentArg = xs.drop(1).bisect('/', xs);

			{
				var argName = new StringView("");
				var argValue = new StringView("");

				currentArg.bisect(argName, ' ', argValue);
				var list = new ArrayList<String>();
				list.add(argValue.toString().strip());

				args.merge(argName.toString().strip(), list, (oldVal, newVal) -> {
					oldVal.addAll(newVal);
					return oldVal;
				});
			}

			if (xs.isEmpty()) {
				break;
			}

			xs = xs.undrop(1);
		}

		return Optional.of(new CommandArguments(command, target, theRest, args));
	}
}
