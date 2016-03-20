
This program is implemented using JDK1.8 with the Gradle build tool.

My word list is taken from the dictionary shipped with Mac OS-X: /usr/share/dict/words 
(with exception for words length less than 5, because avergage English word length is 5.1).

Some important data-structures being used (or implemented):

    o Trie
    o Queue
    o Double-linked list

To play the game, type the following command:

% gradle -q clean compileJava game_play

Other useful gradle tasks:

% gradle test_Trie

% gradle test_Dictionary

Hopefully this was coded mostly according to the spec! :)

