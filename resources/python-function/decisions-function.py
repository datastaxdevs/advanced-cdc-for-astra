from pulsar import Function
import json

class DecisionsFunction(Function):
  def __init__(self):
    pass

  def process(self, input, context):
    logger = context.get_logger()

    logger.info('RECEIVED INPUT==========================')
    logger.info("*"+input+"*")

    # Parse input json
    so_post = json.loads(input)

    # Only accept post type of 1
    if (so_post["_postTypeId"] != 1):
      return

    # No scores under 20
    if (so_post["_score"] < 20):
      return
    
    # Nothing that is unanswered
    if (so_post["_answerCount"] < 1):
      return

    logger.info('RETURNING OUTPUT==========================')
    logger.info("*"+input+"*")

    return input