function interact(self, user)
    print("interacted")
end

function update(self)
    self.position.x = self.position.x + 10
    self.position.y = self.position.y + 10
    --print("updating")
    if(Input:isButtonPressed(0)) then
        self.position.x = Input:getX()
        self.position.y = Graphics:getHeight() - Input:getY()
    end
    if(Input:isButtonPressed(1)) then  print("Right mouse down") end

    print("Mouse Position :" .. Input:getX() .. " : " .. Input:getY())
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

