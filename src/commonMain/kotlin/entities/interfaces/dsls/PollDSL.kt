package entities.interfaces.dsls

import entities.interfaces.Competition
import entities.interfaces.PollAlgorithm
import entities.interfaces.Vote
import entities.types.ScoreMetric

/**
 * DSL for polls.
 */
interface PollDSL<S : ScoreMetric, V : Vote> : SPVoteAlgorithmDSL<S>, LOPVoteAlgorithmDSL<S> {
/**
     * Shortcut which assigns the value to pollAlgorithm.
     */
    operator fun PollAlgorithm<S, V>.unaryMinus()

/**
     * Shortcut which assigns the value to [competition].
     */
    operator fun Competition<S>.unaryMinus()

/**
     * Shortcut to add a vote in votesList.
     */
    operator fun V.unaryPlus()

/**
     * DSL-function which initializes a [Competition].
     */
    fun competition(competitionName: String, compInit: CompetitionDSL<S>.() -> Unit): Competition<S>
}
