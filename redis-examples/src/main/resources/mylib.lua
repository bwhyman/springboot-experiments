
-- increx key byint 1 ubound 2 ex 5 enx
local function expire(keys, args)
    local count = tonumber(args[1])
    local exp = tonumber(args[2])
    local r = redis.call('increx', keys[1], 'byint', 1, 'ubound', count, 'ex', exp, 'enx')
    if(r[2] == 0) then
        return false
    end
    return true
end

redis.register_function('expire_0', expire)
