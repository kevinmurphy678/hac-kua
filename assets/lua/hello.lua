function interact(self, user)
    print("interacted")

end

function update(self)
    self.position.x = self.position.x + 10
    self.position.y = self.position.y + 20
    print("updating")
end

function someFunction(value)
    print("This is some function. Output:  " .. tostring(value))
    return 12345
end

function anotherFunction(value)
    --Call 'someFunction' on the hackable 'Hack1' and then print its value
    result = Call("Hack1", "someFunction", {value})
    print(result)
end

