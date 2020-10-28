// AddCommandParserTest.java

package chopchop.logic.parser.commands;

import java.util.HashMap;

import chopchop.logic.parser.ArgName;
import chopchop.logic.parser.CommandParser;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AddCommandParserTest {

    @Test
    void test_parse() {
        var cases = new HashMap<String, Boolean>();
        var parser = new CommandParser();

        cases.put("add recipe",                                                         false);
        cases.put("add recipe f /name",                                                 false);
        cases.put("add recipe f /ingredient:add",                                       false);
        cases.put("add recipe f /ingredient /qty 5",                                    false);
        cases.put("add recipe f /ingredient x /ingredient",                             false);
        cases.put("add recipe f /ingredient x /ingredient y /qty zzz",                  false);
        cases.put("add recipe f /qty 700",                                              false);

        cases.put("add ingredient",                                                     false);
        cases.put("add ingredient f /name",                                             false);
        cases.put("add ingredient f /qty:add",                                          false);
        cases.put("add ingredient f /qty",                                              false);
        cases.put("add ingredient f /qty /qty",                                         false);
        cases.put("add ingredient f /qty 1 /qty 2",                                     false);
        cases.put("add ingredient f /qty x",                                            false);
        cases.put("add ingredient f /qty x y /qty zzz",                                 false);
        cases.put("add ingredient f /expiry",                                           false);
        cases.put("add ingredient f /expiry /expiry",                                   false);

        cases.put("delete recipe",                                                      false);
        cases.put("delete recipe f /name",                                              false);
        cases.put("delete recipe f /ingredient:delete",                                 false);

        cases.put("delete ingredient",                                                  false);
        cases.put("delete ingredient f /name",                                          false);
        cases.put("delete ingredient f /ingredient:delete",                             false);
        cases.put("delete ingredient f /qty",                                           false);
        cases.put("delete ingredient f /qty /qty",                                      false);

        cases.put("edit recipe",                                                        false);
        cases.put("edit ingredient x",                                                  false);
        cases.put("edit recipe x /ingredient",                                          false);
        cases.put("edit recipe x /ingredient:owo",                                      false);
        cases.put("edit recipe x /ingredient:owo:uwu",                                  false);
        cases.put("edit recipe x /ingredient:add:add",                                  false);
        cases.put("edit recipe x /ingredient:add",                                      false);
        cases.put("edit recipe x /ingredient:edit y",                                   false);
        cases.put("edit recipe x /ingredient:add y",                                    false);
        cases.put("edit recipe x /ingredient:delete y /qty 6",                          false);
        cases.put("edit recipe x /ingredient:delete y /qty z",                          false);

        cases.put("edit recipe x /tag",                                                 false);
        cases.put("edit recipe x /tag:owo",                                             false);
        cases.put("edit recipe x /tag:owo:uwu",                                         false);
        cases.put("edit recipe x /tag:add:add",                                         false);
        cases.put("edit recipe x /tag:add",                                             false);

        cases.put("edit recipe x /step",                                                false);
        cases.put("edit recipe x /step:owo",                                            false);
        cases.put("edit recipe x /step:owo:uwu",                                        false);
        cases.put("edit recipe x /step:add",                                            false);
        cases.put("edit recipe x /step:add:zzz",                                        false);
        cases.put("edit recipe x /step:add:999",                                        false);
        cases.put("edit recipe x /step:add:-3",                                         false);
        cases.put("edit recipe x /step:add:3:3",                                        false);
        cases.put("edit recipe x /step:delete y",                                       false);
        cases.put("edit recipe x /step:delete: y",                                      false);
        cases.put("edit recipe x /step:edit y",                                         false);

        cases.put("edit recipe x /name",                                                false);
        cases.put("edit recipe x /name asdf /name uwu",                                 false);

        cases.put("edit recipe x /qty",                                                 false);
        cases.put("edit recipe x /expiry",                                              false);

        cases.put("edit recipe x /tag:add y",                                           true);
        cases.put("edit recipe x /tag:delete y",                                        true);
        cases.put("edit recipe x /step:add y",                                          true);
        cases.put("edit recipe x /step:edit:4 y",                                       true);
        cases.put("edit recipe x /ingredient:edit y /qty 6",                            true);
        cases.put("edit recipe x /ingredient:add y /qty 1",                             true);
        cases.put("edit recipe x /ingredient:delete y",                                 true);
        cases.put("edit recipe x /ingredient:delete y /step:add z",                     true);


        cases.forEach((k, v) -> {
            assertEquals(v, parser.parse(k).hasValue());
        });


        try {
            var m = EditCommandParser.class.getDeclaredMethod("parseTagEdit", ArgName.class, String.class);
            m.setAccessible(true);

            m.invoke(null, new ArgName("tag:add"), "  owo  ");

            var m2 = EditCommandParser.class.getDeclaredMethod("ensureNoArgsForDeleteAndGetOperationType",
                String.class, String.class, boolean.class);
            m2.setAccessible(true);

            m2.invoke(null, "tag", "kekw", true);
        } catch (Exception e) {
        }
    }
}
