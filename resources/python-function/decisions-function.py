from pulsar import Function

class DecisionsFunction(Function):
  def __init__(self):
    pass

  def process(self, input, context):
    context.get_logger().info(input + '-log')
    #raise Exception('this will not work')  
    return input + '!'