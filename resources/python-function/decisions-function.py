from pulsar import Function

class DecisionsFunction(Function):
  def __init__(self):
    pass

  def process(self, input, context):
    #context.get_logger().info(input + '-log')
    #raise Exception('this will not work')

    # Parse input json
    so_post = json.loads(input)

    # Only accept post type of 1
    if (so_post["postTypeId"] != 1):
      return

    # No scores under 20
    if (so_post["score"] < 20):
      return
    
    # Nothing that is unanswered
    if (so_post["answerCount"] < 1):
      return
    
    # so_post["creationDate"]
    # so_post["answerCount"]
    # so_post["postMessage"]
    # so_post["title"]
    
    # Write new message
    # indexVal = { "title": ""so_post["title"]"", }

    return json.dumps(so_post["title"]) 