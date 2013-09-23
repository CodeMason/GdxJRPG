raw_asm

dim int counter
counter = 5

equivalent to

push int
push counter
dim
push 5
set dim

counter = 10 / 3

push 3
push 10
div
set counter

{
}

if x > 3 closure_a

push closure_a
push 3
pull x
gequal
if

script_asm

int bob
bob = 3

closure_a {
    int anint = 5;
    
}

push 5 queued =
push anint
push int
decl
set