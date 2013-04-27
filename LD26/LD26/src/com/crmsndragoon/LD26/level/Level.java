package com.crmsndragoon.LD26.level;

import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.scenes.scene2d.Stage;

public class Level {
	private Stage m_stage;
	private Stage m_background;
	private Stage m_foreground;
	private InputMultiplexer m_inputMP;
	public Level() {
		m_stage = new Stage();
		m_background = new Stage();
		m_foreground = new Stage();
		m_inputMP = new InputMultiplexer();
		m_inputMP.addProcessor(m_stage);
		m_inputMP.addProcessor(m_background);
		m_inputMP.addProcessor(m_foreground);
	}
	public InputMultiplexer getInputMultiplexer() {
		return m_inputMP;
	}
}
