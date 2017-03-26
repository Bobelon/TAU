Scenario: Alarm ring on 2017-03-25 8:20 (now)

Given set alarm on 2017-03-25 8:20
When now is 2017-03-25 8:20
Then ring


Scenario: Alarm ring on 2017-03-25 8:20 (not now)

Given set alarm on 2017-03-25 8:20
When now is 2017-03-25 9:15
Then not ring


Scenario:  Some examples

Given set alarm on <y>-<m>-<d> <h>:<min>
When now is <ny>-<nm>-<nd> <nh>:<nmin>
Then <result>

Examples:
|y|m|d|h|min|ny|nm|nd|nh|nmin|result|
|1993|03|15|12|13|2017|03|26|11|06|not ring|
|2017|03|26|11|06|1993|03|15|12|13|not ring|
|2017|03|26|11|06|2017|03|26|11|06|ring|
|1993|03|15|12|13|1993|03|15|12|13|ring|