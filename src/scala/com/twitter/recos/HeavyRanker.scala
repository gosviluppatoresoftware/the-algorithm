/* ============================================
   FAIRNESS & QUALITY FIX (by [Tuo Nome])
   ============================================

   Objective: improve fairness and reduce spam bias in ranking algorithm.

   - Caps Tweepcred influence to prevent large-account dominance
   - Adds a small-account bias bonus (+0.1)
   - Adjusts spam penalty dynamically
   - Integrates quality feature for content weighting

   This patch is a placeholder for the Python simulation already tested.
   It can be implemented in the feature calculation phase.
*/

def fairnessAdjustedScore(
  likes: Double,
  retweets: Double,
  recencyHours: Double,
  tweepcred: Double,
  spamProb: Double,
  isSmall: Boolean,
  quality: Double
): Double = {

  // Engagement weight (70% likes + 30% retweets)
  val engagement = (0.7 * likes) + (0.3 * retweets)

  // Recency decay (tweets lose weight over 24h)
  val recencyWeight = Math.exp(-recencyHours / 24.0)

  // Cap Tweepcred influence (max 0.8)
  val cappedTweepcred = Math.min(0.8, tweepcred)

  // Add fairness bonus for small accounts
  val fairnessBonus = if (isSmall) 0.1 else 0.0

  // Adjust spam penalty (less severe for genuine engagement)
  val adjustedSpam = spamProb * 0.5

  // Compute the final fairness-adjusted score
  val score = (engagement * recencyWeight) * (cappedTweepcred + fairnessBonus) * quality * (1.0 - adjustedSpam)

  score
}
