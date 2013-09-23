#function function_name 4
function name, then number of arguments

#include "filename.asm"

class MonsterHandler {
    function test {
        return arg0
    }
}

function function_name arg0 arg1
    call create_group "testgroup"
    call add_to_group "testgroup" arg0
    call add_to_group "testgroup" arg1
    


raw_asm
//======================
// Comments look familiar
//======================
int tim
//push can be 1-*
push 5 3
add
//other commands can have push args too, equivalent of push 3, push 1, add
add 3 1
//you could be retarded though
sub 4 1 2 3 5 //result would be -2 (3-5), with 3, 2, and 1 in the stack in that order
add //which means this would be -2 + 2
mul //and this would be 0 * 1
div //and this would be 4/0 supernova

clear //clears the stack
set tim 2
// the & before a term means it's a pull instead of a push
// a ^ pulls from the context store, which is only valid for the current execution context
push 2 3 &tim
store record 3 //writes into the context store
getstore record //pushes the store value of record onto the stack
unstore record //removes just record from the store
clstore //clears the store

if ( gequal &tim &bill ) {
}

//======================
// Command List
//======================
int ____ = bogus declaration of int
float ____ = declares a float
string ____ = declares a string
bool _____ = declares a bool

push ____ = pushes values onto the stack
pull ____ = pushes the value of a local variable onto the stack
pop = pops off the top stack value
clear = clears the entire stack

//Math and comparison commands pop the top 2 from the stack and push the result onto the stack
add
sub
div
mul
mod
equal
lequal
gequal
greater
less
nequal

inc ____ = increments a declared variable by 1
dec ____ = decrements a declared variable by 1

set ____ = sets declared variable ____ with the pop from the stack

call _____ = calls a Java bound method

//Expert know-what-you're-doing stuff
store _key_ _value_ = places a value with a specific key into the execution context
unstore _key_ = removes a kvp from the execution context
getstore _key_ = pushes the value of a key from the exec ctx onto the stack
clstore = clears the execution context store

return = ends execution of the current closure

push closurename

begin something
    
end

script_asm
int test
3 + (5/2) * 1

push 1
push 2
push 5
div